package uqtr.ecompagnon.service.implement;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import uqtr.ecompagnon.dto.FcmRequestDTO;
import uqtr.ecompagnon.model.*;
import uqtr.ecompagnon.repository.AppUserRepository;
import uqtr.ecompagnon.repository.NotificationRepository;
import uqtr.ecompagnon.util.exception.AppRequestException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service

public class FcmSchedulerServiceImp {

    @Autowired
    private QuestionnaireServiceImpl questionnaireServiceimpl;
    @Autowired
    private SessionServiceImpl sessionServiceimpl;
    @Autowired
    private FcmPushServiceImpl fcmPushService;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private  UtilServiceImpl utilServiceImpl;
    @Autowired
    private  TemplateEngine templateEngine;
    @Autowired
    private Environment env;



    Calendar calendar = Calendar.getInstance();
    @Async
    //@Scheduled(fixedRate = 60000,fixedDelay = 5, initialDelay = 1000, cron = "0 0 7 * * *")//cron cha
    @Scheduled(cron= "0 30 07 * * ?", zone = "Canada/Eastern")//cron cha
    //@Scheduled(fixedRate = 60000)//cron chaque jou à 7h
    public void sendNotification()
    {
        sendNoticationOnQuestDayForAll();
        sendNoticationEveryTwoDaysForAppUserNotFillQuestAvail();

    }

    public void sendNoticationOnQuestDayForAll(){
        Session session=sessionServiceimpl.getActiveSession();
        LocalDate sendDate=LocalDate.now();
        List<String> typeG=Arrays.asList(new String[]{"T11","T12","T3"});
        List<String> typeS=Arrays.asList(new String[]{"T2"});
        List<String> typeF=Arrays.asList(new String[]{"T3"});
        boolean notifSend=false;
        List<GroupEtudiant> existT2=listQuest(sendDate,session.getId(),typeS);
        String sendDateYear=String.valueOf(sendDate.getYear());
        List<AppUser> existT3= appUserRepository.getFinishuserNotFillFormFisrt(sendDate,LocalDateTime.now(),sendDateYear);
        List<GroupEtudiant> groupEtudiantsT11T12=listQuest(sendDate,session.getId(),typeG);
        String topic=null;

        /*si affichage de T11 et T12 et T2 dans la même période,
         * un étudiant recevra au moins une notification et plus
         * en fonction du nombre de groupe auquelles il appartient*/
//pour T11 et T12
        List<Questionnaire> questionnairesG=questionnaireServiceimpl.getByTypeStartDateEqualAndSession(sendDate,session.getId(),typeG);

        if((existT2.size()>0)||(groupEtudiantsT11T12.size()>0)||(existT3.size()>0))
        {
            sendFirtEmail(existT3);
        }
        if(groupEtudiantsT11T12.size()>0)
        {//System.out.println("groupEtudiantsT11T12 ");
            notifSend=false;
            for(GroupEtudiant group:groupEtudiantsT11T12)
            {
                topic="GS-" + group.getId() + '-' + session.getId();
                FcmRequestDTO fcmRequestDTO =new FcmRequestDTO();
                fcmRequestDTO.setToken(null);
                fcmRequestDTO.setTitle("Un questionnaire vous attend sur eCompagnon culturel");
                fcmRequestDTO.setMessage("Un questionnaire vous attend sur l'application eCompagnon culturel!");
                fcmRequestDTO.setTopic(topic);
                fcmRequestDTO.setNbreQuest(groupEtudiantsT11T12.size());
                fcmRequestDTO.setTypes("null");
                fcmPushService.sendMessageWithDataToTopic(fcmRequestDTO);
                notifSend=true;
                // System.out.println("topic "+topic);

            }
            if(notifSend==true)
            {
                updateQuestionnaire(questionnairesG);
            }

        }
        //pour T2
        if(existT2.size()>0)
        {
            //System.out.println("ExistT2 ");
            List<Questionnaire> questionnairesAll=questionnaireServiceimpl.getByTypeStartDateEqualAndSession(sendDate,session.getId(),typeS);
            topic="S-"+ session.getId();
            FcmRequestDTO fcmRequestDTO =new FcmRequestDTO();
            fcmRequestDTO.setToken(null);
            fcmRequestDTO.setTitle("Un questionnaire vous attend sur eCompagnon culturel");
            fcmRequestDTO.setMessage("Un questionnaire vous attend sur l'application eCompagnon culturel !");
            fcmRequestDTO.setTopic(topic);
            fcmRequestDTO.setNbreQuest(groupEtudiantsT11T12.size());
            fcmRequestDTO.setTypes("null");
            fcmPushService.sendMessageWithDataToTopic(fcmRequestDTO);
           // System.out.println("topic "+topic);

            updateQuestionnaire(questionnairesAll);
        }
        //pour T3
        if(existT3.size()>0)
        {
            //System.out.println("ExistT2 ");
            List<Questionnaire> questionnairesAll=questionnaireServiceimpl.getByTypeStartDateEqualT3(sendDate,typeF);
            topic="F-T3";
            FcmRequestDTO fcmRequestDTO =new FcmRequestDTO();
            fcmRequestDTO.setToken(null);
            fcmRequestDTO.setTitle("Un questionnaire vous attend sur eCompagnon culturel");
            fcmRequestDTO.setMessage("Un questionnaire vous attend sur l'application eCompagnon culturel !");
            fcmRequestDTO.setTopic(topic);
            fcmRequestDTO.setNbreQuest(groupEtudiantsT11T12.size());
            fcmRequestDTO.setTypes("null");
            fcmPushService.sendMessageWithDataToTopic(fcmRequestDTO);
           // System.out.println("topic "+topic);

            updateQuestionnaire(questionnairesAll);
        }


    }

    public Notification getNotification(Session session,Questionnaire questionnaire)
    {
        Notification notification=new Notification();
        if(questionnaire.getType().equals("T2"))
        {
            notification.setType("S");
        }
        else
        {
            notification.setType("GS");
        }

        notification.setGroupEtudiant(questionnaire.getGroupEtudiant());
        notification.setId(0L);
        notification.setSession(session);
        notification.setDateSend(LocalDateTime.now());
        notification.setQuestionnaire(questionnaire);
        notification.setStatus(1L);


        return notification;

    }
    public void updateQuestionnaire(List<Questionnaire> questionnaires)
    {
        List<Questionnaire> questionnairesFilter=new ArrayList<>();
        List<Notification> notifications=new ArrayList<>();
        //if(group==null)
        //{
        if(questionnaires.size()>0)
        {
            questionnaires.stream().
                    forEach(questionnaireFilter -> {
                        Questionnaire questionnaire=questionnaireFilter;
                        questionnaire.setFisrtNotific(1L);
                        questionnaire.setLastNotific(LocalDateTime.now());
                        questionnairesFilter.add(questionnaire);
                        notifications.add(getNotification(questionnaire.getSession(),questionnaire));

                    });
            notificationRepository.saveAll(notifications);

        }

        questionnaireServiceimpl.saveAll(questionnairesFilter);
    }

    public void sendNoticationEveryTwoDaysForAppUserNotFillQuestAvail(){
        Session session=sessionServiceimpl.getActiveSession();
        List<String> typeG=Arrays.asList(new String[]{"T11","T12"});
        List<String> typeS=Arrays.asList(new String[]{"T2"});
        boolean notifSend2Days=false;
        List<Questionnaire> questionnaires=questionnaireServiceimpl.
                getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionFirstNotif(LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDate.now(),
                        session.getId());
        List<Questionnaire> questionnairesT3=questionnaireServiceimpl.
                getByStartDateLessThanEqualAndEndDateGreaterThanAndSessionFirstNotifT3(LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDate.now());
//Pour T11 et T12
        List<AppUser> appUsersInGroup=this.appUserRepository.getAppUserInGroupNotFillQuestAvail(LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDate.now(),session.getId());
        //Pour T2
        List<AppUser> appUsersAll=this.appUserRepository.getAllAppUserWithoutGroupNotFillQuestAvail(LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDate.now(),session.getId(),typeS);
//Pour T3
        String sendDateYear=String.valueOf((LocalDateTime.now()).getYear());
        List<AppUser> appUserT3= appUserRepository.getFinishuserNotFillFormEveryTwoday(LocalDateTime.now(),
                                                                                        LocalDateTime.now(),
                                                                                            LocalDate.now(),
                                                                                            sendDateYear);

        if((appUsersAll.size()>0)||(appUsersInGroup.size()>0)||(appUserT3.size()>0))
        {
            sendEmailPerTwoDays(appUsersInGroup,appUsersAll,appUserT3);
        }

        if(appUsersInGroup.size()>0)
        {
            notifSend2Days=false;
            for(AppUser appUser:appUsersInGroup)
            {
               // System.out.println("appUsersInGroup");
                //System.out.println(notifSend2Days);
                if(appUser.getTokenNotific() != null)
                {
                   // System.out.println(notifSend2Days);
                    System.out.println(appUser.getTokenNotific());
                    FcmRequestDTO fcmRequestDTO =new FcmRequestDTO();
                    fcmRequestDTO.setToken(appUser.getTokenNotific());
                    fcmRequestDTO.setTitle("Un questionnaire vous attend sur eCompagnon culturel");
                    fcmRequestDTO.setMessage("Un questionnaire vous attend sur l'application eCompagnon culturel !");
                    fcmRequestDTO.setTopic(null);
                    fcmRequestDTO.setNbreQuest(questionnaires.size());
                    fcmRequestDTO.setUserId(Math.toIntExact(appUser.getIdUsers()));
                    fcmRequestDTO.setTypes("null");
                    fcmPushService.sendMessageWithDataToToken(fcmRequestDTO);
                    notifSend2Days=true;
                }


            }
            if(notifSend2Days == true)
            {
                updateQuestionnaire(questionnaires);
            }

        }

        //Pour T2

        if(appUsersAll.size()>0)
        { notifSend2Days=false;
            //System.out.println("appUsersAll ");
            // System.out.println(appUsersAll.size());
            for(AppUser appUser:appUsersAll)
            {
                if(appUser.getTokenNotific() != null)
                {
                    //System.out.println(appUser.getTokenNotific());
                    FcmRequestDTO fcmRequestDTO =new FcmRequestDTO();
                    fcmRequestDTO.setToken(appUser.getTokenNotific());
                    fcmRequestDTO.setTitle("Un questionnaire vous attend sur eCompagnon culturel");
                    fcmRequestDTO.setMessage("Un questionnaire vous attend sur l'application eCompagnon culturel !");
                    fcmRequestDTO.setTopic(null);
                    fcmRequestDTO.setNbreQuest(questionnaires.size());
                    fcmRequestDTO.setUserId(Math.toIntExact(appUser.getIdUsers()));
                    fcmRequestDTO.setTypes("null");
                    fcmPushService.sendMessageWithDataToToken(fcmRequestDTO);
                    notifSend2Days=true;
                }


            }
            if(notifSend2Days==true)
            {
                updateQuestionnaire(questionnaires);
            }

        }


        //Pour T3

        if(appUserT3.size()>0)
        { notifSend2Days=false;
           // System.out.println("appUserT3 ");
            // System.out.println(appUsersAll.size());
            for(AppUser appUser:appUserT3)
            {
                if(appUser.getTokenNotific() != null)
                {
                   // System.out.println(appUser.getTokenNotific());
                    FcmRequestDTO fcmRequestDTO =new FcmRequestDTO();
                    fcmRequestDTO.setToken(appUser.getTokenNotific());
                    fcmRequestDTO.setTitle("Un questionnaire vous attend sur eCompagnon culturel");
                    fcmRequestDTO.setMessage("Un questionnaire vous attend sur l'application eCompagnon culturel !");
                    fcmRequestDTO.setTopic(null);
                    fcmRequestDTO.setNbreQuest(questionnaires.size());
                    fcmRequestDTO.setUserId(Math.toIntExact(appUser.getIdUsers()));
                    fcmRequestDTO.setTypes("null");
                    fcmPushService.sendMessageWithDataToToken(fcmRequestDTO);
                    notifSend2Days=true;
                }


            }
            if(notifSend2Days==true)
            {
                updateQuestionnaire(questionnairesT3);
            }

        }

    }

    public void sendFirtEmail(List<AppUser> existT3)
    {
        Session session=sessionServiceimpl.getActiveSession();
        // List<String> typeG=Arrays.asList(new String[]{"T11","T12"});
        Double maxMailSend  = Double.valueOf(env.getProperty("maxMailSend"));
        List<String> mailSend = new ArrayList<>();
        String[] mailSendArray=null;
        List<String> typeS=Arrays.asList(new String[]{"T2"});
        List<AppUser> appUsersInGroup=this.appUserRepository.getAppUserInGroupNotFillQuestAvailFirst(LocalDateTime.now(),
                LocalDateTime.now(),
                session.getId());

        List<AppUser> appUsersAll=this.appUserRepository.getAllAppUserWithoutGroupNotFillQuestAvailFirst(LocalDate.now(),
                LocalDateTime.now(),
                session.getId(),typeS);

        List<AppUser> appUserT3=existT3;

        if((appUsersInGroup.size()>0) || (appUsersAll.size()>0) || (appUserT3.size()>0))
        {
            List<AppUser> appUserInGroup = appUsersInGroup,appUsersSend=null,appUserAll = appUsersAll;

            //Etudiants communs aux deux liste
            appUserAll.addAll(appUserInGroup);
            appUserAll.addAll(appUserT3);
            appUsersSend = Lists.newArrayList(Sets.newHashSet(appUserAll));

            //Récupération des  courriels commun aux deux liste
            if(appUsersSend.size()>0)
            {
                mailSend=appUsersSend.stream()
                   .map(AppUser::getMailUsers)
                    .collect(Collectors.toList());
            }
            //Subdivision de la liste des courriels en petit nombre si la taille de la liste dépasse 23

            int nbreGroupSend=0;
            if(mailSend.size()>maxMailSend)
            {
                nbreGroupSend=(int)Math.ceil(mailSend.size()/maxMailSend);
                List<List<String>> mailSendSubSets = Lists.partition(mailSend, maxMailSend.intValue());
                //System.out.println(appUsersCom.toString());
                for (int i =0; i<nbreGroupSend;i++)
                {
                    mailSendArray=(mailSendSubSets.get(i)).toArray(new String[0]);
                    try
                    {
                        sendEmail(mailSendArray);
                    }
                    catch (Exception e)
                    {
                        throw new AppRequestException("Le courriel n'a pas pu été envoyé pour le groupe" + i);

                    }

                }

            }
            else
            {
                mailSendArray=mailSend.toArray(new String[0]);
                try
                {
                    sendEmail(mailSendArray);
                }
                catch (Exception e)
                {
                    throw new AppRequestException("Le courriel n'a pas pu été envoyé pour le seul groupe"+e);

                }


            }




        }


    }

    public void sendEmailPerTwoDays( List<AppUser> appUsersInGroup,List<AppUser> appUsersAll,List<AppUser> appUsersT3)
    {
        Session session=sessionServiceimpl.getActiveSession();
        Double maxMailSend  = Double.valueOf(env.getProperty("maxMailSend"));
        List<String> mailSend = new ArrayList<>();
        String[] mailSendArray=null;
        List<String> typeS=Arrays.asList(new String[]{"T2"});

        if((appUsersInGroup.size()>0) || (appUsersAll.size()>0)|| (appUsersT3.size()>0))
        {
            List<AppUser> appUserInGroup = appUsersInGroup,appUsersSend=null,appUserAll = appUsersAll;
            //Etudiants communs aux deux liste
            appUserAll.addAll(appUserInGroup);
            appUserAll.addAll(appUsersT3);
            appUsersSend = Lists.newArrayList(Sets.newHashSet(appUserAll));

            //Récupération des  courriels commun aux deux liste
            if(appUsersSend.size()>0)
            {
                mailSend=appUsersSend.stream()
                        .map(AppUser::getMailUsers)
                        .collect(Collectors.toList());
            }
            //Subdivision de la liste des courriels en petit nombre si la taille de la liste dépasse 26

            int nbreGroupSend=0;
            if(mailSend.size()>maxMailSend)
            {
                nbreGroupSend=(int)Math.ceil(mailSend.size()/maxMailSend);
                List<List<String>> mailSendSubSets = Lists.partition(mailSend, maxMailSend.intValue());
                //System.out.println(appUsersCom.toString());
                for (int i =0; i<nbreGroupSend;i++)
                {
                    mailSendArray=(mailSendSubSets.get(i)).toArray(new String[0]);
                    try
                    {
                        sendEmail(mailSendArray);
                    }
                    catch (Exception e)
                    {
                        throw new AppRequestException("Le courriel n'a pas pu été envoyé pour le groupe" + i);

                    }

                }

            }
            else
            {
                mailSendArray=mailSend.toArray(new String[0]);
                try
                {
                    sendEmail(mailSendArray);
                }
                catch (Exception e)
                {
                    throw new AppRequestException("Le courriel n'a pas pu été envoyé pour le seul groupe");

                }


            }




        }


    }


    public void sendEmail(String[] emails) {
        String contenuDow= "Un questionnaire vous attend sur l'application eCompagnon culturel !";
        String entete = "Positionnez-vous dans eCompagnon culturel";
        String textLinkPs ="android";
        String textLinkAs = "iphone";
        String textSite = "www.uqtr.ca/mediateursculturels";
        String textCourriel = "mediateur@uqtr.ca";
        //String textLinkOpen = "eCompagnon culturel";
        String textLinkOpen = null;
       // String contenuOpen= "Vous pouvez ouvrir directement l'application si elle est déjà installée, à partir de ce second lien:";
        String contenuOpen= null;
        String subject = "Un questionnaire vous attend sur eCompagnon culturel";
        Context context = new Context();
        String linkPs=env.getProperty("linkPlayStore");
        String linkAs=env.getProperty("linkAppleStore");
        String linkSite = env.getProperty("linkSite");
        String linkCourriel =env.getProperty("linkCourriel");
        String linkOpen=null;
        //String name="à toutes et à tous";
        String name=null;
        context.setVariable("linkPs", linkPs);
        context.setVariable("linkAs", linkAs);
        context.setVariable("linkOpen", linkOpen);
        context.setVariable("linkSite", linkSite);
        context.setVariable("linkCourriel", linkCourriel);
        context.setVariable("contenuDow", contenuDow);
        context.setVariable("contenuOpen", contenuOpen);
        context.setVariable("entete", entete);
        context.setVariable("textLinkPs", textLinkPs);
        context.setVariable("textLinkAs", textLinkAs);
        context.setVariable("textLinkOpen", textLinkOpen);
        context.setVariable("textSite", textSite);
        context.setVariable("textCourriel", textCourriel);
        context.setVariable("subject", subject);
        context.setVariable("name", name);
        //context.setVariable("name", request.getFirstname()+",");
        String process = templateEngine.process("questionnaire", context);
        // String link=" http://localhost:8100/login";
        //utilServiceImpl.sendMail(request.getMailUsers(), buildEmail(request.getFirstname(),link,entete,contenu,textLink),subject);
        try  {
            utilServiceImpl.sendMultiMailsGmail(emails, process, subject);
        }
        catch (Exception e)
        {
            throw new AppRequestException("Impossible d\'envoyer ce courriel");

        }


    }
    public List<GroupEtudiant>  listQuest(LocalDate dateAffiche, Long session,List<String> types)
    {
        return questionnaireServiceimpl.getByGBTypeStartDateEqualAndSession(dateAffiche,session,types);
    }
}

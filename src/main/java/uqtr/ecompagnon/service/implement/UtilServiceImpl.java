package uqtr.ecompagnon.service.implement;

import org.apache.poi.util.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;

@Component
public class UtilServiceImpl {

    @Qualifier("getJavaMailSender")
    @Autowired
    private JavaMailSender mailSenderGmail;

    @Qualifier("getJavaMailSenderOutLook")
    @Autowired
    private JavaMailSender mailSenderOutlook;

    @Autowired
    private Environment env;


    /**
     * This method will send compose and send the message
     */
    public void sendMailGmail(String to, String body,String object) {

        try {
            MimeMessage mimeMessage=mailSenderGmail.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(body,true);
            helper.setTo(to);
            helper.setSubject(object);
            mailSenderGmail.send(mimeMessage);

        }catch (MessagingException e)
        {
            System.out.println(e);
            throw  new IllegalStateException("Failed to send email");
        }
    }

    public void sendMultiMailsGmail(String[] to, String body,String object){
        String[] bccMail = null;
        String sendmail = null;
        //sendmail=ArrayUtils.get(to,0);//Récupération du premier courriel
       /// bccMail= ArrayUtils.remove(to,0);//Supression du premier courriel de la liste
        try {
            MimeMessage mimeMessage=mailSenderGmail.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(body,true);
            helper.setBcc(to);
            helper.setSubject(object);
            // helper.setFrom("ECompagon Culturel");
            mailSenderGmail.send(mimeMessage);

        }catch (MessagingException e)
        {
            //LOGGER.error("failed to send email",e);
            throw  new IllegalStateException("Failed to send email");
        }

    }



    public void sendMultiMailsOutLook(String[] to, String body,String object){
        String[] bccMail = null;
        String sendmail = null;
        //sendmail=ArrayUtils.get(to,0);//Récupération du premier courriel
        //bccMail= ArrayUtils.remove(to,0);//Supression du premier courriel de la liste
        try {


            MimeMessage mimeMessage=mailSenderOutlook.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(body,true);
            //helper.setTo(to);
            helper.setBcc(to);
            //helper.setTo(to);
            helper.setFrom(env.getProperty("emailOutLook"));
            helper.setSubject(object);
            // helper.setFrom("ECompagon Culturel")
            mailSenderOutlook.send(mimeMessage);

        }catch (MessagingException e)
        {

            //LOGGER.error("failed to send email",e);
            throw  new IllegalStateException("Failed to send email");
        }

    }

    public void sendMailsOutLook(String to, String body,String object){
        try {
            MimeMessage mimeMessage=mailSenderOutlook.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setText(body,true);
            helper.setTo(to);
            helper.setFrom(env.getProperty("emailOutLook"));
            helper.setSubject(object);
            // helper.setFrom("ECompagon Culturel");
            mailSenderOutlook.send(mimeMessage);

        }catch (MessagingException e)
        {

            //LOGGER.error("failed to send email",e);
            throw  new IllegalStateException("Failed to send email");
        }

    }

   /* *//**
     * This method will send a pre-configured message
     *//*
    public void sendPreConfiguredMail(String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }*/
}

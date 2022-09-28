package uqtr.ecompagnon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.QuestionQuestionnaire;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByMailUsers(String email);

    Optional<AppUser> findByCPUsers(String CP);

    Optional<AppUser> findByMailUsersAndPasswordUsers(String email,String password);




    @Query(value ="select * from public.\"appUser\" " +
            "where exists"+
                        "(select id from public.questionnaire q " +
                        "where \"fisrtNotific\"=1  " +
                        "and Date(\"lastNotific\")+2=:dateDujour " +
                        "and \"startDate\"<=:dateAffiche " +
                        "and \"endDate\">:dateAffiche1 and session=:session " +
                        "and q.type in (:types))" +
            "and \"idUsers\" not in " +
                                "(SELECT distinct \"idUsers\" " +
                                "FROM public.\"appUser\", lateral jsonb_array_elements(\"formField\") " +
                                "where (value->>'id')\\:\\:int in  " +
                                                            "(select id from public.questionnaire q" +
                                                            " where \"fisrtNotific\"=1 " +
                                                            " and Date(\"lastNotific\")+2=:dateDujour " +
                                                            "and \"startDate\"<=:dateAffiche " +
                                                            "and \"endDate\">:dateAffiche1 and session=:session " +
                                                            "and q.type in (:types))" +
                                ") " +
            "and \"finissant\"=false "
            , nativeQuery = true)
    List<AppUser> getAllAppUserWithoutGroupNotFillQuestAvail(LocalDateTime dateAffiche,
                                                               LocalDateTime dateAffiche1,
                                                               LocalDate dateDujour,
                                                               Long session,
                                                             List<String> types);
    @Query(value ="select * from public.\"appUser\" app, " +
            "public.etudiant e, public.etudiantgroupe ep  " +
            "where app.\"CPUsers\"=e.\"etudCP\" " +
            "and e.id=ep.etudiant " +
            "and exists (select id from public.questionnaire q " +
                        "where \"fisrtNotific\"=1 " +
                        "and Date(\"lastNotific\")+2=:dateDujour " +
                        "and \"startDate\"<=:dateAffiche " +
                        "and \"endDate\">:dateAffiche1 and session=:session " +
                        "and ep.groupe=q.\"groupEtudiant\" " +
                        " and q.session=ep.session " +
                        "and q.type in ('T11'))" +

            "and \"idUsers\" not in " +
                                "(SELECT distinct \"idUsers\" " +
                                "FROM public.\"appUser\", lateral jsonb_array_elements(\"formField\") " +
                                "where (value->>'id')\\:\\:int in  " +
                                                                "(select id from public.questionnaire q " +
                                                                " where \"fisrtNotific\"=1 " +
                                                                " and Date(\"lastNotific\")+2=:dateDujour " +
                                                                "and \"startDate\"<=:dateAffiche " +
                                                                "and \"endDate\">:dateAffiche1 and session=:session " +
                                                                "and ep.groupe=q.\"groupEtudiant\" " +
                                                                " and q.session=ep.session " +
                                                                "and q.type in ('T11'))" +

                                ") " +
            "and \"finissant\"=false " +


            "union " +

            "select * from public.\"appUser\" app, " +
            "public.etudiant e, public.etudiantgroupe ep  " +
            "where app.\"CPUsers\"=e.\"etudCP\" " +
            "and e.id=ep.etudiant " +
            "and exists (select id from public.questionnaire q " +
            "where \"fisrtNotific\"=1 " +
            "and Date(\"lastNotific\")+2=:dateDujour " +
            "and \"startDate\"<=:dateAffiche " +
            "and \"endDate\">:dateAffiche1 and session=:session " +
            "and ep.groupe=q.\"groupEtudiant\" " +
            " and q.session=ep.session " +
            "and q.type in ('T12'))" +

            "and \"idUsers\" not in " +
            "(SELECT distinct \"idUsers\" " +
            "FROM public.\"appUser\", lateral jsonb_array_elements(\"formField\") " +
            "where (value->>'id')\\:\\:int in  " +
            "(select id from public.questionnaire q " +
            " where \"fisrtNotific\"=1 " +
            " and Date(\"lastNotific\")+2=:dateDujour " +
            "and \"startDate\"<=:dateAffiche " +
            "and \"endDate\">:dateAffiche1 and session=:session " +
            "and ep.groupe=q.\"groupEtudiant\" " +
            " and q.session=ep.session " +
            "and q.type in ('T12'))" +

            ") " +
            "and \"finissant\"=false "
            , nativeQuery = true)
    List<AppUser> getAppUserInGroupNotFillQuestAvail(LocalDateTime dateAffiche,
                                              LocalDateTime dateAffiche1,
                                              LocalDate dateDujour,
                                              Long session
                                            );


    @Query(value ="select * from public.\"appUser\" " +
            "where exists"+
            "(select id from public.questionnaire q " +
            "where \"fisrtNotific\" is null  " +
            "and Date(\"lastNotific\") is null " +
            "and Date(\"startDate\")=:dateAffiche " +
            "and \"endDate\">:dateAffiche1 and session=:session " +
            "and q.type in (:types)) " +
            "and \"idUsers\" not in " +
            "(SELECT distinct \"idUsers\" " +
            "FROM public.\"appUser\", lateral jsonb_array_elements(\"formField\") " +
            "where (value->>'id')\\:\\:int in  " +
            "(select id from public.questionnaire q" +
            " where \"fisrtNotific\" is null " +
            " and Date(\"lastNotific\") is null " +
            "and Date(\"startDate\")=:dateAffiche " +
            "and \"endDate\">:dateAffiche1 and session=:session " +
            "and q.type in (:types))" +
            ") " +
            "and \"finissant\"=false "
            , nativeQuery = true)
    List<AppUser> getAllAppUserWithoutGroupNotFillQuestAvailFirst(LocalDate dateAffiche,
                                                             LocalDateTime dateAffiche1,
                                                             Long session,
                                                             List<String> types);
    @Query(value ="select * from public.\"appUser\" app, " +
            "public.etudiant e, public.etudiantgroupe ep  " +
            "where app.\"CPUsers\"=e.\"etudCP\" " +
            "and e.id=ep.etudiant " +
            "and exists (select id from public.questionnaire q " +
            "where \"fisrtNotific\" is null " +
            "and Date(\"lastNotific\") is null " +
            "and Date(\"startDate\")=:dateAffiche " +
            "and \"endDate\">:dateAffiche1 and session=:session " +
            "and ep.groupe=q.\"groupEtudiant\" " +
            " and q.session=ep.session " +
            "and q.type in ('T11'))" +

            "and \"idUsers\" not in " +
            "(SELECT distinct \"idUsers\" " +
            "FROM public.\"appUser\", lateral jsonb_array_elements(\"formField\") " +
            "where (value->>'id')\\:\\:int in  " +
            "(select id from public.questionnaire q " +
            " where \"fisrtNotific\" is null " +
            " and Date(\"lastNotific\") is null " +
            "and Date(\"startDate\")=:dateAffiche " +
            "and \"endDate\">:dateAffiche1 and session=:session " +
            "and ep.groupe=q.\"groupEtudiant\" " +
            " and q.session=ep.session " +
            "and q.type in ('T11'))" +

            ") " +
            "and \"finissant\"=false " +

            "union  "+//union entre T11 et T12

            "select * from public.\"appUser\" app, " +
            "public.etudiant e, public.etudiantgroupe ep  " +
            "where app.\"CPUsers\"=e.\"etudCP\" " +
            "and e.id=ep.etudiant " +
            "and exists (select id from public.questionnaire q " +
            "where \"fisrtNotific\" is null " +
            "and Date(\"lastNotific\") is null " +
            "and Date(\"startDate\")=:dateAffiche " +
            "and \"endDate\">:dateAffiche1 and session=:session " +
            "and ep.groupe=q.\"groupEtudiant\" " +
            " and q.session=ep.session " +
            "and q.type in ('T12'))" +

            "and  \"idUsers\" not in " +
            "(SELECT distinct \"idUsers\" " +
            "FROM public.\"appUser\", lateral jsonb_array_elements(\"formField\") " +
            "where (value->>'id')\\:\\:int in  " +
            "(select id from public.questionnaire q " +
            " where \"fisrtNotific\" is null " +
            " and Date(\"lastNotific\") is null " +
            "and Date(\"startDate\")=:dateAffiche " +
            "and \"endDate\">:dateAffiche1 and session=:session " +
            "and ep.groupe=q.\"groupEtudiant\" " +
            " and q.session=ep.session " +
            "and q.type in ('T12'))" +

            ") " +
            "and \"finissant\"=false "
            , nativeQuery = true)
    List<AppUser> getAppUserInGroupNotFillQuestAvailFirst(LocalDateTime dateAffiche,
                                                     LocalDateTime dateAffiche1,
                                                     Long session
                                                     );


    @Query("select distinct app from appUser app   " +
            "where app.finissant=false " +
            "and app.annfin=:yearNow " +
            "and app.CPUsers=:cpUser")
    AppUser verifyFinishuser(String cpUser, String yearNow);

    @Query(value ="select * from public.\"appUser\" app  " +
            "where app.annfin=:sendDateYear " +
            "and finissant is false  " +
            "and exists (select id from public.questionnaire q " +
                    "where \"fisrtNotific\" is null " +
                    "and Date(\"lastNotific\") is null " +
                    "and Date(\"startDate\")=:dateAffiche " +
                    "and \"endDate\">:dateAffiche1 " +
                    "and q.type in ('T3')) " +
            "and  \"idUsers\" not in " +
            "(SELECT distinct \"idUsers\" " +
            "FROM public.\"appUser\", lateral jsonb_array_elements(\"formField\") " +
            "where (value->>'id')\\:\\:int in  " +
            "(select id from public.questionnaire q " +
            " where \"fisrtNotific\" is null " +
            " and Date(\"lastNotific\") is null " +
            "and Date(\"startDate\")=:dateAffiche " +
            "and \"endDate\">:dateAffiche1 " +
            "and q.type in ('T3'))" +

            ") "
            , nativeQuery = true)
    List<AppUser> getFinishuserNotFillFormFisrt(LocalDate dateAffiche,
                                                LocalDateTime dateAffiche1,
                                                String sendDateYear);

    @Query(value ="select * from public.\"appUser\" app  " +
            "where app.annfin=:sendDateYear " +
            "and finissant is false " +
            "and exists (select id from public.questionnaire q " +
                        "where \"fisrtNotific\"=1 " +
                        "and Date(\"lastNotific\")+2=:dateDujour " +
                        "and \"startDate\"<=:dateAffiche " +
                        "and \"endDate\">:dateAffiche1  " +
                        "and q.type in ('T3')) " +
            "and  \"idUsers\" not in " +
                            "(SELECT distinct \"idUsers\" " +
                            "FROM public.\"appUser\", lateral jsonb_array_elements(\"formField\") " +
                            "where (value->>'id')\\:\\:int in  " +
                            "(select id from public.questionnaire q " +
                            " where \"fisrtNotific\" =1 " +
                            " and Date(\"lastNotific\")+2=:dateDujour " +
                            "and \"startDate\"<=:dateAffiche " +
                            "and \"endDate\">:dateAffiche1 " +
                            "and q.type in ('T3'))" +

                            ") "
            , nativeQuery = true)
    List<AppUser> getFinishuserNotFillFormEveryTwoday(LocalDateTime dateAffiche,
                                                      LocalDateTime dateAffiche1,
                                                      LocalDate dateDujour,
                                                    String sendDateYear);


    @Query("select distinct a.firstname,a.lastname,a.mailUsers,a.CPUsers,a.enabled from appUser a " +
            "order by a.firstname")
     List<Object> findAllUsers();
}




package uqtr.ecompagnon.service;


import uqtr.ecompagnon.dto.LoginResponseDTO;
import uqtr.ecompagnon.dto.ReponseDTO;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.Etudiant;
import uqtr.ecompagnon.model.Questionnaire;
import uqtr.ecompagnon.model.Reponse;

import java.util.List;

public interface ReponseService {
    <S extends Reponse> S save(Reponse reponse);
    LoginResponseDTO saveAll(List iterable, String CP, Long questionnaire,
                             String type,String repQ1T0);
    Iterable<Reponse> findAll();
    List<Reponse> getByQuestionnaire(Long questionnaire);
    List<Questionnaire> getAllQuestionnaireResponse();
    List<Questionnaire> getQuestionnaireResponseBySession(Long session);
    List<Reponse> getReponseByQuestionnaireAndSession(Long questionnaire,Long session);
    List<Reponse> getReponseByGroupAndSessionAndIdEtud(Long group,Long session,Long idEtud);
    List<Etudiant> getEtudiantFromReponseByGroupAndSession(Long group, Long session);


}

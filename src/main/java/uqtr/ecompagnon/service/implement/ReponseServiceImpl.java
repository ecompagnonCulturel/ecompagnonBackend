package uqtr.ecompagnon.service.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.dto.JsonStringDTO;
import uqtr.ecompagnon.dto.LoginResponseDTO;
import uqtr.ecompagnon.model.*;
import uqtr.ecompagnon.repository.AppUserRepository;
import uqtr.ecompagnon.repository.QuestionnaireRepository;
import uqtr.ecompagnon.repository.ReponseRepository;
import uqtr.ecompagnon.service.ReponseService;
import uqtr.ecompagnon.util.exception.AppRequestException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class ReponseServiceImpl implements ReponseService {
    private final ReponseRepository reponseRepository;
    private final AppUserServiceImpl appUserService;
    private final AppUserRepository appUserRepository;
    private final QuestionnaireRepository questionnaireRepository;
    private SessionServiceImpl sessionService;

    @Override
    public <S extends Reponse> S save(Reponse reponse) {

        return (S) reponseRepository.save(reponse);
    }

    @Override
    public LoginResponseDTO saveAll(List iterable, String CP, Long questionnaire,
                                    String type,
                                    String repQ1T0) {


        ObjectMapper objectMapper = new ObjectMapper();
        AppUser appUser = (AppUser) appUserService.loadUserByUserCP(CP);
        JsonStringDTO jsonStringDTO = new JsonStringDTO();
        jsonStringDTO.setId(questionnaire);
        jsonStringDTO.setValue(type);
        String formfielded = appUser.getFormField();

        if(type.equals("T3"))//Mise à jour de l'utilisateur finissant
        {
            appUser.setFinissant(true);
        }
        else
        {
           // appUser.setFinissant(false);

            if(type.equals("T0"))//Mise à jour de l'utilisateur finissant
            {
                appUser.setAnnfin(repQ1T0);
            }
        }

        if (formfielded == null) {
          //  System.out.println(formfielded);
            try {
                appUser.setFormField('[' + objectMapper.writeValueAsString(jsonStringDTO) + ']');
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {

            formfielded = this.addSrting(formfielded, jsonStringDTO);
            appUser.setFormField(formfielded);

        }
        reponseRepository.saveAll(iterable);
        appUserRepository.save(appUser);

        LoginResponseDTO loginRespons = new LoginResponseDTO(appUser.getIdUsers(),
                appUser.getLastname(),
                appUser.getFirstname(),
                appUser.getMailUsers(),
                appUser.getCPUsers(),
                appUser.getFormField(),
                appUser.getTokenNotific()
        );
        return loginRespons;
    }

    @Override
    public Iterable<Reponse> findAll() {
        return reponseRepository.findAll();
    }

    @Override
    public List<Reponse> getByQuestionnaire(Long questionnaireParam) {
        Questionnaire questionnaire=questionnaireRepository.findById(questionnaireParam)
                .orElseThrow(() ->
                        new IllegalStateException(
                                "questionnaire not found"
                        ));
        return reponseRepository.getReponseByQuestionnaireOrderByCp(questionnaire);
    }

    @Override
    public List<Questionnaire> getAllQuestionnaireResponse(){

        return reponseRepository.getAllQuestionnaireResponse();

    }

    @Override
    public List<Questionnaire> getQuestionnaireResponseBySession(Long session) {
        Session sessionGet=sessionService.getById(session)
                .orElseThrow(() ->
                        new AppRequestException("Cette session n'existe pas"));

        return reponseRepository.getQuestionnaireResponseBySession(sessionGet);
    }



    @Override
    public List<Reponse> getReponseByQuestionnaireAndSession(Long questionnaire, Long session) {
        return reponseRepository.getReponseByQuestionnaireAndSession(questionnaire,session);
    }

    //Ajout d'un objet json à un tableau d'objet json existant
    public String addSrting(String initial, JsonStringDTO text) {
        ObjectMapper objectMapper = new ObjectMapper();
        String pattern = "\\[";
        initial = (initial.replaceAll(pattern, ""));
        initial = (initial.replaceAll("]", ""));
        initial = initial.trim();
        try {
            initial = '[' + initial + ',' + (objectMapper.writeValueAsString(text)) + ']';
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return initial;
    }

    @Override
    public List<Reponse> getReponseByGroupAndSessionAndIdEtud(Long group, Long session, Long IdEtud) {
        return reponseRepository.getReponseByGroupAndSessionAndIdEtud(group, session,IdEtud);
    }

    @Override
    public List<Etudiant> getEtudiantFromReponseByGroupAndSession(Long group, Long session) {
        return reponseRepository.getEtudiantFromReponseByGroupAndSession(group,session);
    }


}

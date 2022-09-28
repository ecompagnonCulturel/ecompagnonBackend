package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.LoginResponseDTO;
import uqtr.ecompagnon.dto.RegistrationRequestDTO;
import uqtr.ecompagnon.dto.RegistrationResponseDTO;
import uqtr.ecompagnon.dto.ReponseRequestDTO;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.Reponse;
import uqtr.ecompagnon.service.implement.AppUserServiceImpl;
import uqtr.ecompagnon.service.implement.ReponseServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api/Reponse")
@AllArgsConstructor
public class ReponseController {
    private final ReponseServiceImpl reponseService;

    @PostMapping(value ="/add")
    public ResponseEntity<Object> register(@RequestBody ReponseRequestDTO reponseRequestDTO)
    {
        try {

            LoginResponseDTO loginResponseDTO =reponseService.saveAll(reponseRequestDTO.getQuestionRep(),reponseRequestDTO.getCP(),
                    reponseRequestDTO.getQuestionnaire(),reponseRequestDTO.getType(),reponseRequestDTO.getRepQ1T0());

            return new ResponseEntity(loginResponseDTO, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Comments.out.println(Comments.toString());
            return new ResponseEntity("Erreur d'enregistrement du Questionnaire"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }


    @GetMapping("/getAllQuestionnaireResponse")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllQuestionnaireResponse() {
        try {
            return new ResponseEntity<>(reponseService.getAllQuestionnaireResponse(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getByQuestionnaire")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getByQuestionnaire(@RequestParam(value = "questionnaire")  Long questionnaire) {
        try {
            return new ResponseEntity<>(reponseService.getByQuestionnaire(questionnaire), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getQuestionnaireBySession")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getQuestionnaireResponseBySession(@RequestParam(value = "session")  Long session) {
        try {
            return new ResponseEntity<>(reponseService.getQuestionnaireResponseBySession(session), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @GetMapping("/getReponseByQuestionnaireAndSession")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getReponseByQuestionnaireAndSession(@RequestParam(value = "questionnaire")  Long questionnaire,
                                                                      @RequestParam(value = "session")  Long session) {
        try {
            return new ResponseEntity<>(reponseService.getReponseByQuestionnaireAndSession(questionnaire,session), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée1", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getReponseByGroupAndSessionAndIdEtud")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getReponseByGroupAndSessionAndIdEtud(@RequestParam(value = "group")  Long group,
                                                                      @RequestParam(value = "session")  Long session,
                                                                       @RequestParam(value = "idEtud")  Long idEtud) {
        try {
            return new ResponseEntity<>(reponseService.getReponseByGroupAndSessionAndIdEtud(group,session,idEtud), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée1", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getEtudiantFromReponseByGroupAndSession")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getEtudiantFromReponseByGroupAndSession(@RequestParam(value = "group")  Long group,
                                                                       @RequestParam(value = "session")  Long session) {
        try {
            return new ResponseEntity<>(reponseService.getEtudiantFromReponseByGroupAndSession(group,session), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée1", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

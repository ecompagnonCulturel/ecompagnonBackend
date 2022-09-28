package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.NoteDTO;
import uqtr.ecompagnon.dto.QuestionnaireDTO;
import uqtr.ecompagnon.model.Questionnaire;
import uqtr.ecompagnon.model.Session;
import uqtr.ecompagnon.service.implement.QuestionQuestionnaireServiceImpl;
import uqtr.ecompagnon.service.implement.QuestionnaireServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@AllArgsConstructor
@RequestMapping("/api/Questionnaire")
public class QuestionnaireController {

    private QuestionnaireServiceImpl questionnaireService;
    private QuestionQuestionnaireServiceImpl questionQuestionnaireService;
   // DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
   //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  // DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/updateQuestionnaire")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> updateQuestionnaire(@RequestBody QuestionnaireDTO questionnaireDTO) {
        try {

           questionnaireService.save(questionnaireDTO);

            return new ResponseEntity("questionnaire enregistré avec succès" + questionnaireDTO.toString(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Erreur d'enregistrement du Note"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/addQuestionnaire")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addQuestionnaire(@RequestBody QuestionnaireDTO questionnaireDTO) {
        try {

            questionnaireService.saveNew(questionnaireDTO);

            return new ResponseEntity("questionnaire enregistré avec succès" + questionnaireDTO.toString(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Erreur d'enregistrement du Note"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getGeneralQuest")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getGeneralQuest(@RequestParam(value = "type")String type,@RequestParam(value = "status")Long status,@RequestParam(value = "id")Long id) {
        try {

            return new ResponseEntity<>(questionQuestionnaireService.getByTypeContainingAndStatusAndId(type,status,id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @GetMapping("/getGeneralQuestT0")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getGeneralQuestT0(@RequestParam(value = "type")String type,@RequestParam(value = "status")Long status) {
        try {

            return new ResponseEntity<>(questionQuestionnaireService.getByQuestionnaireTypeAndStatus(type,status), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getQuestionnaireByStartDateAndEndDate")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getByStartDateAfterAndEndDateBefore(@RequestParam(value = "StartD") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  StartD,@RequestParam(value = "EndD") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime EndD) {
        try {

            return new ResponseEntity<>(questionnaireService.getQuestionnaireByStartDateAndEndDate(StartD,EndD), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/getAllQuestDate")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllQuestDate(@RequestParam(value = "StartD") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  StartD, @RequestParam(value = "EndD") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime EndD, @RequestParam(value = "session") Long session) {
        try {

            return new ResponseEntity<>(questionQuestionnaireService.getByQuestionnaireDateTypeSession(StartD,EndD,session), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllQuestByDateSessionGroupe")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllQuestByDateSessionGroupe(@RequestParam(value = "StartD")
                                                                     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  StartD,
                                                                 @RequestParam(value = "EndD")
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                                                         LocalDateTime EndD,
                                                                 @RequestParam(value = "session") Long session,
                                                                @RequestParam(value = "cp") String cp)
    {
        try {

            return new ResponseEntity<>(questionQuestionnaireService
                    .getQuestionQByDateSessionCP(StartD,EndD,session,cp),
                    HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @GetMapping("/getByTypeAndGroupEtudiantAndSession")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getByTypeAndGroupEtudiantAndSession(@RequestParam(value = "type") String type, @RequestParam(value = "groupe") Long groupe,@RequestParam(value = "session") Long session) {
        try {

            return new ResponseEntity<>(questionnaireService.getByTypeAndGroupEtudiantAndSession(type,groupe,session), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @GetMapping("/getAllQuestQuestionnaire")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllQuestQuestionnaire() {
        try {

            return new ResponseEntity<>(questionQuestionnaireService.getAllQuestionQuestionnaire(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/getAllQuest")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllQuest() {
        try {
            return new ResponseEntity<>(questionnaireService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getQuestionnaireByGroupEtudiant")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getQuestionnaireByGroupEtudiant(@RequestParam(value = "groupe") Long groupe) {
        try {
            return new ResponseEntity<>(questionnaireService.getQuestionnaireByGroupEtudiant(groupe), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getQuestionnaireGroupEtudiant")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getQuestionnaireGroupEtudiant() {
        try {
            return new ResponseEntity<>(questionnaireService.getQuestionnaireGroupEtudiant(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getByQuestionnaireTypeAndDate")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getByQuestionnaireTypeAndDate(@RequestParam(value = "type") String type,@RequestParam(value = "StartD") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  StartD, @RequestParam(value = "EndD") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime EndD) {
        try {
            return new ResponseEntity<>(questionQuestionnaireService.getByQuestionnaireTypeAndDate(type,StartD,EndD), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @DeleteMapping("/delQuestionnaire")
    public ResponseEntity<Object> deleteQuestionnaire(@RequestParam(value = "questionnaire")  Long questionnaire,
                                                      @RequestParam(value = "session")  Long session) {
        try {
            questionnaireService.deleteById(questionnaire,session);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("le questionnaire n'a pas pu être supprimé", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}

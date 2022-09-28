package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.service.implement.QuesionnaireGroupeServiceImpl;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
@RequestMapping("/api/QuestionnaireGroupe")
public class QuestionnaireGroupeController {
    private QuesionnaireGroupeServiceImpl quesionnaireGroupeService;


  /*  @GetMapping("/getGeneralQuestG")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getGeneralQuest(@RequestParam(value = "type")String type, @RequestParam(value = "status")Long status, @RequestParam(value = "id")Long id) {
        try {

            return new ResponseEntity<>(quesionnaireGroupeService.getByTypeContainingAndStatusAndId(type,status,id), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @GetMapping("/getGeneralQuestT0G")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getGeneralQuestT0(@RequestParam(value = "type")String type,@RequestParam(value = "status")Long status) {
        try {

            return new ResponseEntity<>(quesionnaireGroupeService.getByQuestionnaireTypeAndStatus(type,status), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }*/

    @GetMapping("/getQuestionnaireByDateG")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getQuestionnaireByDate(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        try {

            return new ResponseEntity<>(quesionnaireGroupeService.getQuestionnaireByDate(date), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/getAllQuestDateG")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllQuestDate(@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime  date, @RequestParam(value = "session") Long session) {
        try {

            return new ResponseEntity<>(quesionnaireGroupeService.getQuestionnaireByDateAndSession(date,session), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @GetMapping("/getByTypeAndGroupEtudiantAndSessionG")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getByTypeAndGroupEtudiantAndSession(@RequestParam(value = "type") String type, @RequestParam(value = "groupe") Long groupe,@RequestParam(value = "session") Long session) {
        try {

            return new ResponseEntity<>(quesionnaireGroupeService.getByTypeAndGroupEtudiantAndSession(type,groupe,session), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

/*
    @GetMapping("/getAllQuestQuestionnaireG")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllQuestQuestionnaire() {
        try {

            return new ResponseEntity<>(quesionnaireGroupeService.getAllQuestionQuestionnaire(), HttpStatus.OK);
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


    @DeleteMapping("/delQuestionnaire")
    public ResponseEntity<Object> deleteQuestionnaire(@RequestParam(value = "id")  Long id) {
        try {
            questionnaireService.deleteById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("le questionnaire n'a pas pu être supprimé", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }*/
}

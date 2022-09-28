package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.ActiviteDTO;
import uqtr.ecompagnon.dto.CommentDTO;
import uqtr.ecompagnon.service.implement.ComplementServiceImpl;

@RestController
@RequestMapping("/api/Complement")
@AllArgsConstructor
public class ComplementController {
    private final ComplementServiceImpl complementService;

    @GetMapping("/getByQuestionnaire")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getByQuestionnaire(@RequestParam(value = "questionnaire") Long questionnaire) {
        try {
            return new ResponseEntity<>(complementService.getByQuestionnaire(questionnaire), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la donnée n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllComplement")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllComplement() {
        try {
            return new ResponseEntity<>(complementService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la donnée n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllActivitiesFromComplement")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllActivitiesFromComplement() {
        try {
            return new ResponseEntity<>(complementService.getAllActivitiesFromComplement(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la donnée n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.ActiviteDTO;
import uqtr.ecompagnon.model.Activite;
import uqtr.ecompagnon.service.implement.ActiviteServiceImpl;

@RestController
@RequestMapping("/api/Activite")
@AllArgsConstructor
public class ActiviteController {
    private final ActiviteServiceImpl activiteService;


    @GetMapping("/getBySession")
    public ResponseEntity<Object> getBySession(@RequestParam(value = "Session") Long session) {
        try {
            return new ResponseEntity<>(activiteService.getBySession(session), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllActivities")
    public ResponseEntity<Object> getAllActivities() {
        try {
            return new ResponseEntity<>(activiteService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/addActivity")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addNewActivity(@RequestBody ActiviteDTO activiteDTO) {
        try {

            activiteService.save(activiteDTO);

            return new ResponseEntity("activité enregistrée avec succès" + activiteDTO, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Comments.out.println(Comments.toString());
            return new ResponseEntity("Erreur d'enregistrement de l' activité"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/delActivite")
    public ResponseEntity<Object> deleteQuestionnaire(@RequestParam(value = "id")  Long id) {
        try {
            activiteService.deleteById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("l'activité n'a pas pu être supprimé", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}

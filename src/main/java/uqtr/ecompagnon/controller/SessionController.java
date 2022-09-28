package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.model.Session;
import uqtr.ecompagnon.service.implement.SessionServiceImpl;

@RestController
@RequestMapping("/api/Session")
@AllArgsConstructor
public class SessionController {


    private final SessionServiceImpl sessionServiceImpl;

    @GetMapping("/getAllSession")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllSession() {
        try {
            return new ResponseEntity<>(sessionServiceImpl.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }

    @GetMapping("/getActiveSession")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getActiveSession() {
        try {
            return new ResponseEntity<>(sessionServiceImpl.getActiveSession(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getSessionOrderByStartDate")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getSessionOrderByStartDate() {
        try {
            return new ResponseEntity<>(sessionServiceImpl.getSessionOrderByStartDate(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/delSession")
    public ResponseEntity<Object> deleteSession(@RequestParam(value = "id")  Long id) {
        try {
            sessionServiceImpl.delQuestionnairetById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la session n'a pas pu être supprimée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PostMapping("/addSession")
    public ResponseEntity<Object> addNewSession(@RequestBody Session session) {
        try {

            sessionServiceImpl.save(session);

            return new ResponseEntity("session enregistrée avec succès" + session.toString(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Erreur d'enregistrement de la session"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

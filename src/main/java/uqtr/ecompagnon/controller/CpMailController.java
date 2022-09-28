package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.model.CpMail;
import uqtr.ecompagnon.service.implement.CpMailServiceImpl;

@RestController
@RequestMapping("api/CPEmail")
@AllArgsConstructor
public class CpMailController {

    private final CpMailServiceImpl CpMailServiceImpl;


    @PostMapping("/addCP_Email")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addNewCP_Email(@RequestBody CpMail CpMail) {
        try {

            CpMailServiceImpl.addCpMail(CpMail);

            return new ResponseEntity("CP_Email enregistré avec succès" + CpMail.toString(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // CP_Email.out.println(CP_Email.toString());
            return new ResponseEntity("Erreur d'enregistrement du CP_Email", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getCP_EmailList")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getCP_EmailList() {
        try {
            return new ResponseEntity<>(CpMailServiceImpl.getAllCpMail(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getCP_Email/{id}")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getCP_Email(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(CpMailServiceImpl.getCpMailById(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la CP_Emaile n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getCPEmailByEmail/{mail}")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> findByCpeMail(@PathVariable String mail) {
        try {
            return new ResponseEntity<>(CpMailServiceImpl.getCpMailByMail(mail), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la CP_Emaile n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllCpMail")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllCpMail() {
        try {
            return new ResponseEntity<>(CpMailServiceImpl.getAllCpMail(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la CP_Emaile n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/delCP_Email/{id}")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> deleteCP_Email(@PathVariable Long id) {
        try {
            CpMailServiceImpl.deleteCpMail(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la CP_Emaile n'a pas être supprimée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

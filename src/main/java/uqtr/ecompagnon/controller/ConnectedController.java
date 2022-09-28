package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.model.Connected;
import uqtr.ecompagnon.service.implement.ConnectedServiceImpl;

@RestController
@RequestMapping("/api/Connected")
@AllArgsConstructor
public class ConnectedController {

    private final ConnectedServiceImpl connectedServiceImpl;

    @PostMapping("/addConnected")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addNewConnected(@RequestBody Connected comment) {
        try {

            connectedServiceImpl.save(comment);

            return new ResponseEntity("Connexion enregistrée avec succès" + comment.toString(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Connecteds.out.println(Connecteds.toString());
            return new ResponseEntity("Erreur d'enregistrement du Connecteds", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getConnectedByUser")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getConnectedByUser(@RequestParam(value = "mail") String mail) {
        try {
            return new ResponseEntity<>(connectedServiceImpl.getConnectedByUser(mail), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.ActiviteDTO;
import uqtr.ecompagnon.dto.UserDTO;
import uqtr.ecompagnon.service.implement.UsersServiceImpl;

@RestController
@RequestMapping("/api/User")
@AllArgsConstructor
public class UsersController {

    private final UsersServiceImpl usersServiceImpl;

   /* @GetMapping("/getUsersMail/{mail}")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> findByMailUsers(@PathVariable String mail) {
        try {
            return new ResponseEntity<>(usersServiceImpl.findByMailUsers(mail), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }*/

    @PostMapping("/addUsers")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addNewActivity(@RequestBody UserDTO userDTO) {
        try {

            usersServiceImpl.save(userDTO);

            return new ResponseEntity("Utilisateur enregistrée avec succès" + userDTO, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Comments.out.println(Comments.toString());
            return new ResponseEntity("Erreur d'enregistrement de l'utilisateur"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}

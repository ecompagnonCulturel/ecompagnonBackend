package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.model.Profile;
import uqtr.ecompagnon.service.implement.ProfileServiceImpl;

@RestController
@RequestMapping("api/Profile")
@AllArgsConstructor
public class ProfileController {

    private final ProfileServiceImpl profileServiceImpl;

    @PostMapping("/addProfile")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addNewProfile(@RequestBody Profile profile) {
        try {

            profileServiceImpl.save(profile);

            return new ResponseEntity("Profile enregistré avec succès" + profile.toString(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Profile.out.println(Profile.toString());
            return new ResponseEntity("Erreur d'enregistrement du Profile", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getProfileList")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getProfileList() {
        try {
            return new ResponseEntity<>(profileServiceImpl.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getProfile/{id}")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getProfile(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(profileServiceImpl.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la Profilee n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getProfileByNom/{nom}")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getProfileByUser(@PathVariable String nom) {
        try {
            return new ResponseEntity<>(profileServiceImpl.getProfileByNom(nom), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les système dans Profilee n'ont pas pu être récupéré", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @DeleteMapping("/delProfile/{id}")
    //@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> deleteProfile(@PathVariable Long id) {
        try {
            profileServiceImpl.delProfiletById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la Profile n'a pas être supprimée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}

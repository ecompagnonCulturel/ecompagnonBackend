package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uqtr.ecompagnon.repository.TypeRessourceRepository;

@RestController
@RequestMapping("/TypeRessource")
@AllArgsConstructor
public class TypeRessourceController {

    private final TypeRessourceRepository typeRessourceRepository;

    @GetMapping("/getTypeRessource")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getTypeRessource() {
        try {
            return new ResponseEntity<>(typeRessourceRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.model.Cours;
import uqtr.ecompagnon.model.Prof;
import uqtr.ecompagnon.service.implement.CoursServiceImpl;
import uqtr.ecompagnon.service.implement.CourseResourceServiceImpl;

@RestController
@RequestMapping("/api/Cours")
@AllArgsConstructor
public class CoursController {
    private final CourseResourceServiceImpl courseResourceService;
    private final CoursServiceImpl coursService;

    @GetMapping("/getAllCours")
    public ResponseEntity<Object> getAllCours() {
        try {
            return new ResponseEntity<>(coursService.getAllCours(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @GetMapping("/getAllCoursInUse")
    public ResponseEntity<Object> getAllProfInUse() {
        try {
            return new ResponseEntity<>(courseResourceService.findAllCours(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/addCours")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addProf(@RequestBody Cours cours) {
        try {
            Cours coursSave=coursService.save(cours);
            return new ResponseEntity<>(coursSave, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Erreur dans l'enregistrement de ce cours"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/delCours")
    public ResponseEntity<Object> delCours(@RequestParam(value = "id")  Long id) {
        try {
            coursService.deleteById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("le cours n'a pas pu être supprimé", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}

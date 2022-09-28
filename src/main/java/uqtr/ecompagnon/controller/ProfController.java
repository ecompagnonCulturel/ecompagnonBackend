package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.EtudiantDTO;
import uqtr.ecompagnon.model.Etudiant;
import uqtr.ecompagnon.model.Prof;
import uqtr.ecompagnon.service.implement.CourseResourceServiceImpl;
import uqtr.ecompagnon.service.implement.ProfServiceImpl;

@RestController
@RequestMapping("/api/Prof")
@AllArgsConstructor
public class ProfController {

    private final ProfServiceImpl profService;
    private final CourseResourceServiceImpl courseResourceService;

    @GetMapping("/getAllProf")
    public ResponseEntity<Object> getAllStudent() {
        try {
            return new ResponseEntity<>(profService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllProfInUse")
    public ResponseEntity<Object> getAllProfInUse() {
        try {
            return new ResponseEntity<>(courseResourceService.findAllProf(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/addProf")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addProf(@RequestBody Prof prof) {
        try {
            Prof profSave=profService.save(prof);
            return new ResponseEntity<>(profSave, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Erreur dans l'enregistrement de ce formateur"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/delProf")
    public ResponseEntity<Object> delProf(@RequestParam(value = "id")  Long id) {
        try {
            profService.deleteById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("le formateur n'a pas pu être supprimé", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

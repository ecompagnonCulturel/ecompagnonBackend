package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.EtudiantGroupDTO;
import uqtr.ecompagnon.dto.GroupEtudiantDTO;
import uqtr.ecompagnon.model.GroupEtudiant;
import uqtr.ecompagnon.service.implement.GroupEtudiantServiceImpl;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/GroupEtudiant")
public class GroupEtudiantController {
    private final GroupEtudiantServiceImpl groupEtudiantService;

    @PostMapping("/addGroupEtudiant")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addGroupEtudiant(@RequestBody EtudiantGroupDTO etudiantGroupDTO) {
        try {


            groupEtudiantService.saveGroupeEtudiant(etudiantGroupDTO.getGroupEtudiant(), etudiantGroupDTO.getEtudiantId());
            return new ResponseEntity("groupeEtudiant enregistré avec succès" + etudiantGroupDTO.toString(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Erreur d'enregistrement de la liste"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @PostMapping("/addGroup")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addGroup(@RequestBody GroupEtudiantDTO groupEtudiantDTO) {
        try {


            groupEtudiantService.save(groupEtudiantDTO);
            return new ResponseEntity("groupeEtudiant enregistré avec succès" + groupEtudiantDTO.toString(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Erreur d'enregistrement du Note"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getGroupEtudiant")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getGroupEtudiant() {
        try {

            return new ResponseEntity<>(groupEtudiantService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/delGroupEtudiant")
    public ResponseEntity<Object> delGroupEtudiant(@RequestParam(value = "id")  Long id) {
        try {
            groupEtudiantService.deleteById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("le groupe n'a pas pu être supprimé", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @GetMapping("/getGroupEtudiantBySessionAndType")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getGroupEtudiantBySessionAndType(@RequestParam(value = "session") Long session,@RequestParam(value = "types") List<String> types) {
        try {
            return new ResponseEntity<>(groupEtudiantService.getGroupEtudiantBySessionAndType(session,types), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}

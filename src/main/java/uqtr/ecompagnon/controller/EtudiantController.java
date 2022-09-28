package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uqtr.ecompagnon.dto.EtudiantDTO;
import uqtr.ecompagnon.dto.UploadResponseDTO;
import uqtr.ecompagnon.model.Etudiant;
import uqtr.ecompagnon.service.implement.EtudiantServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/Etudiant")
@AllArgsConstructor
public class EtudiantController {
     private final EtudiantServiceImpl etudiantService;
    private HttpServletRequest request;
    @GetMapping("/getAllStudent")
    public ResponseEntity<Object> getAllStudent() {
        try {
            return new ResponseEntity<>(etudiantService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllEtudiantOrderByFirstName")
    public ResponseEntity<Object> getAllEtudiantOrderByFirstName() {
        try {
            return new ResponseEntity<>(etudiantService.getAllEtudiantOrderByFirstName(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/addEtudiant")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addEtudiant(@RequestBody EtudiantDTO request) {
        try {
            Etudiant etudiant=etudiantService.save(request);
            return new ResponseEntity<>(etudiant, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Un étudiant avec ce code permanent existe déjà"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PostMapping("/addEtudiantByFile")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addEtudiantByFile(@RequestParam("file") MultipartFile file) {
        try {


            UploadResponseDTO uploadResponseDTO = etudiantService.saveAllByFile(file, request);
            return new ResponseEntity(uploadResponseDTO, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Erreur d'enregistrement des étudiants" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/delEtudiant")
    public ResponseEntity<Object> delEtudiant(@RequestParam(value = "id")  Long id) {
        try {
            etudiantService.deleteById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("l'étudiant n'a pas pu être supprimé", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uqtr.ecompagnon.dto.CheckListRequestDTO;
import uqtr.ecompagnon.dto.GroupEtudiantDTO;
import uqtr.ecompagnon.dto.UploadResponseDTO;
import uqtr.ecompagnon.service.implement.EtudiantGroupeServiceImpl;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/EtudiantGroupe")
@AllArgsConstructor
public class EtudiantGroupeController {

    private final EtudiantGroupeServiceImpl etudiantGroupeService;
    private HttpServletRequest request;


    @GetMapping("/getByEtudiantGroupeSession")
    public ResponseEntity<Object> getByEtudiantGroupeSession(@RequestParam(value = "CP") String cp, @RequestParam(value = "Groupe") Long groupe, @RequestParam(value = "Session") Long session) {
        try {
            return new ResponseEntity<>(etudiantGroupeService.getByEtudiantAndGroupEtudiantAndSession(cp, groupe, session), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllGroupeEtudiantBySession")
    public ResponseEntity<Object> getAllGroupeEtudiantBySession(@RequestParam(value = "Session") Long session) {
        try {
            return new ResponseEntity<>(etudiantGroupeService.getAllGroupeEtudiantBySession(session), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllEtudiantByGroup")
    public ResponseEntity<Object> getAllEtudiant(@RequestParam(value = "Group") Long group) {
        try {
            return new ResponseEntity<>(etudiantGroupeService.getAllEtudiantByGroup(group), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllGroupeEtudiant")
    public ResponseEntity<Object> getAllGroupeEtudiant() {
        try {
            return new ResponseEntity<>(etudiantGroupeService.getAllGroupeEtudiant(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllEtudiant")
    public ResponseEntity<Object> getAllEtudiant() {
        try {
            return new ResponseEntity<>(etudiantGroupeService.getAllEtudiant(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getBySessionAndEtudiant")
    public ResponseEntity<Object> getBySessionAndEtudiant(@RequestParam(value = "session") Long session, @RequestParam(value = "cp") String cp) {
        try {
            return new ResponseEntity<>(etudiantGroupeService.getBySessionAndEtudiant(session, cp), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getBySessionAndEtudiantList")
    public ResponseEntity<Object> getBySessionAndEtudiantList(@RequestParam(value = "session") Long session, @RequestParam(value = "cp") String cp) {
        try {
            return new ResponseEntity<>(etudiantGroupeService.getBySessionAndEtudiantList(session, cp), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/addEtudiantGroup")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addEtudiantGroup(@RequestParam("file") MultipartFile file, @RequestParam("groupe") Long groupe) {
        try {


            UploadResponseDTO uploadResponseDTO = etudiantGroupeService.saveAll(file, groupe, request);
            return new ResponseEntity(uploadResponseDTO, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Erreur d'enregistrement du Note" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/saveSomeStudentInGroup")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> saveSomeStudentInGroup(@RequestBody CheckListRequestDTO checkListRequestDTO) {
        try {


            CheckListRequestDTO checkListRequestDTOResp = etudiantGroupeService.saveSomeStudentInGroup(checkListRequestDTO);
            return new ResponseEntity(checkListRequestDTOResp, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Erreur d'enregistrement de la liste" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/deleteByGroupEtudiantAndEtudiantAndSession")
    public ResponseEntity<Object> deleteByGroupEtudiantAndEtudiantAndSession(@RequestParam(value = "group") Long group,
                                                                             @RequestParam(value = "idEtud") Long idEtud,
                                                                             @RequestParam(value = "session") Long session) {
        try {
            etudiantGroupeService.deleteByGroupEtudiantAndEtudiantAndSession(group, idEtud, session);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être supprimées" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    @GetMapping("/getAllEtudiantNotInGroup")
    public ResponseEntity<Object> getAllEtudiantNotInGroup(@RequestParam(value = "group") Long group) {
        try {
            return new ResponseEntity<>(etudiantGroupeService.getAllEtudiantNotInGroup(group), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les données n'ont pas pu être récupérées" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}

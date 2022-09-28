package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.NoteDTO;
import uqtr.ecompagnon.service.implement.NoteServiceImpl;

@RestController
@RequestMapping("/api/Note")
@AllArgsConstructor
public class NoteController {


    private final NoteServiceImpl noteServiceImpl;

    @PostMapping("/addNote")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addNewNote(@RequestBody NoteDTO noteDTO) {
        try {

            noteServiceImpl.save(noteDTO);

            return new ResponseEntity("Note enregistré avec succès" + noteDTO.toString(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Erreur d'enregistrement du Note"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getNoteList")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getNoteList() {
        try {
            return new ResponseEntity<>(noteServiceImpl.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getNoteById")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getNote(@RequestParam(value = "id") Long id) {
        try {
            return new ResponseEntity<>(noteServiceImpl.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la Notee n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getNoteByUserAndResource")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getNoteByUser(@RequestParam(value = "user") long user, @RequestParam(value = "resource") long resource) {
        try {
            return new ResponseEntity<>(noteServiceImpl.getNoteByUserAndResource(user, resource), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les système dans Notee n'ont pas pu être récupéré", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @DeleteMapping("/delNote")
    public ResponseEntity<Object> deleteNote(@RequestParam(value = "id")  Long id) {
        try {
            noteServiceImpl.delNotetById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la Notee n'a pas être supprimée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}

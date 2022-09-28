package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.CommentDTO;
import uqtr.ecompagnon.dto.CommentUpdateDTO;
import uqtr.ecompagnon.service.implement.CommentServiceImpl;

@RestController
@RequestMapping("/api/Comment")
@AllArgsConstructor
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;



    @PostMapping("/addComment")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addNewComments(@RequestBody CommentUpdateDTO commentUpdateDTO) {
        try {

            commentServiceImpl.save(commentUpdateDTO);

            return new ResponseEntity("Comments enregistré avec succès" + commentUpdateDTO.toString(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Comments.out.println(Comments.toString());
            return new ResponseEntity("Erreur d'enregistrement du Commentaires"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PostMapping("/updateComment")
    public ResponseEntity<Object> updateComment(@RequestBody CommentUpdateDTO commentUpdateDTO) {
        try {


           commentServiceImpl.update(commentUpdateDTO);

            return new ResponseEntity("Comments enregistré avec succès" + commentUpdateDTO.toString(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Comments.out.println(Comments.toString());
            return new ResponseEntity("Erreur d'enregistrement du Commentaires"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getCommentList")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getCommentsList() {
        try {
            return new ResponseEntity<>(commentServiceImpl.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getComment")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getComments(@RequestParam(value = "id") Long id) {
        try {
            return new ResponseEntity<>(commentServiceImpl.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la Commentse n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getCommentsByUser")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getCommentsByUser(@RequestParam(value = "user") long user, @RequestParam(value = "resource") long resource) {
        try {
            return new ResponseEntity<>(commentServiceImpl.getCommentByUser(user, resource), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les commentaires n'ont pas pu être récupéré", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getCommentsByResource")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllCommentByResource( @RequestParam(value = "resource") long resource) {
        try {
            return new ResponseEntity<>(commentServiceImpl.getAllCommentByResource(resource), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("les commentaires n'ont pas pu être récupéré", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @DeleteMapping("/delComment")
    //@CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> deleteComments(@RequestParam(value = "id")Long id) {
        try {
            commentServiceImpl.delCommenttById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la Comment n'a pas être supprimée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}

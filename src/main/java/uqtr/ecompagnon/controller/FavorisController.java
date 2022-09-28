package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.FavorisDTO;
import uqtr.ecompagnon.service.implement.FavorisServiceImpl;

@RestController
@RequestMapping("/api/Favoris")
@AllArgsConstructor
public class FavorisController {

    private final FavorisServiceImpl favorisServiceImpl;

    @PostMapping("/addFavoris")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addNewFavoris(@RequestBody FavorisDTO favorisDTO) {
        try {

            favorisServiceImpl.save(favorisDTO);

            return new ResponseEntity("Favoris enregistré avec succès" + favorisDTO.toString(), HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Favoris.out.println(Favoris.toString());
            return new ResponseEntity("Erreur d'enregistrement du Favoris"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getFavorisList")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getFavorisList() {
        try {
            return new ResponseEntity<>(favorisServiceImpl.getAllFavoris(), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getFavoris")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getFavoris(@RequestParam(value = "idFavoris") Long id) {
        try {
            return new ResponseEntity<>(favorisServiceImpl.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la Favorise n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getFavorisByUserAndResources")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getFavorisByUserAndResources(@RequestParam(value ="FavUser") long user, @RequestParam(value = "FavResource") long resource) {
        try {
            return new ResponseEntity<>(favorisServiceImpl.getFavorisByUserAndResources(user, resource), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les système dans Favorise n'ont pas pu être récupéré", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getFavorisByUser")
    //   @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getFavorisByUser(@RequestParam(value = "FavUser") long user) {
        try {

            return new ResponseEntity<>(favorisServiceImpl.getFavorisByUser(user,1), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            return new ResponseEntity("les favoris n'ont pas pu être récupéré", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @DeleteMapping("/delFavorise")
    public ResponseEntity<Object> deleteFavoris(@RequestParam(value = "idFavoris") Long id) {
        try {
            favorisServiceImpl.delFavoristById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la Favorise n'a pas être supprimée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

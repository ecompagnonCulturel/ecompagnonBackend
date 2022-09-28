package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uqtr.ecompagnon.dto.ResourceDTO;
import uqtr.ecompagnon.dto.UploadResponseDTO;
import uqtr.ecompagnon.service.implement.ResourcesServiceImpl;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/Resources")
@AllArgsConstructor
public class ResourcesController {

    private final ResourcesServiceImpl resourcesServiceImpl;
    private HttpServletRequest request;


    @GetMapping("/getAllResources")
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINREAD')")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllResources() {
        try {
            return new ResponseEntity<>(resourcesServiceImpl.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getResourceBytRDesc/{tRDesc}")
    //  @CrossOrigin(origins = "http://localhost:4200")
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINREAD')")
    public ResponseEntity<Object> getResourceBytRDesc(@PathVariable String tRDesc) {
        try {
            return new ResponseEntity<>(resourcesServiceImpl.getResourceBytRDesc(tRDesc), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getResourceById/{id}")
   // @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINREAD')")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getResourceById(@PathVariable long id) {
        try {
            return new ResponseEntity<>(resourcesServiceImpl.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/addRessource")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addRessource(@RequestBody ResourceDTO resourceDTO) {
        try {

            resourcesServiceImpl.save(resourceDTO);

            return new ResponseEntity(resourceDTO, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Comments.out.println(Comments.toString());
            return new ResponseEntity("Erreur d'enregistrement de le la ressource"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


    @DeleteMapping("/delRessource")
    public ResponseEntity<Object> deleteRessource(@RequestParam(value = "id")  Long id) {
        try {
            resourcesServiceImpl.deleteById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la ressource n'a pas pu être supprimé", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PostMapping("/addResourceByFile")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addResourceByFile(@RequestParam("file") MultipartFile file
            ,@RequestParam("session") Long session) {
        try {


            UploadResponseDTO uploadResponseDTO = resourcesServiceImpl.saveAllByFile(file,session,request);
            return new ResponseEntity(uploadResponseDTO, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Note.out.println(Note.toString());
            return new ResponseEntity("Erreur d'enregistrement des ressources" + e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

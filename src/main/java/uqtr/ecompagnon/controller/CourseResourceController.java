package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.CoursResourceDTO;
import uqtr.ecompagnon.model.Cours;
import uqtr.ecompagnon.model.CourseResourse;
import uqtr.ecompagnon.repository.CoursRepository;
import uqtr.ecompagnon.repository.ResourcesRepository;
import uqtr.ecompagnon.service.implement.CoursServiceImpl;
import uqtr.ecompagnon.service.implement.CourseResourceServiceImpl;
import uqtr.ecompagnon.service.implement.ResourcesServiceImpl;

@RestController
@RequestMapping("/api/CourseResource")
@AllArgsConstructor
public class CourseResourceController {
    private final CourseResourceServiceImpl courseResourceService;
    private final ResourcesServiceImpl resourcesService;
    private final CoursServiceImpl coursService;

    @GetMapping("/getByCoursResResource")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getByCoursResResource(@RequestParam(value = "ressource") long ressource) {
        try {
            return new ResponseEntity<>(courseResourceService.getByCoursResResource(ressource), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les cours n'ont pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllRessource")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllRessource() {
        try {
            return new ResponseEntity<>(courseResourceService.getAllRessource(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les ressources n'ont pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/getCourseResourseByResourceAndCours")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getCourseResourseByResourceAndCours(@RequestParam(value = "ressource") long ressource,@RequestParam(value = "cours") long cours) {
        try {
            return new ResponseEntity<>(courseResourceService.getCourseResourseByResourceAndCours(ressource,cours), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les cours n'ont pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getCoursBySession")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getCoursBySession() {
        try {
            return new ResponseEntity<>(coursService.getCoursBySession(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les ressources n'ont pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getRessourceByCours")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getRessourceByCours(@RequestParam(value = "cours") long cours) {
        try {
            return new ResponseEntity<>(resourcesService.getRessourceByCours(cours), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les ressources n'ont pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getByRessourceAndCours")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getByRessourceAndCours(@RequestParam(value = "ressource") long ressource,@RequestParam(value = "cours") long cours) {
        try {
            return new ResponseEntity<>(resourcesService.getByRessourceAndCours(ressource,cours), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les ressources n'ont pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getRessourceBySession")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getRessourceBySession() {
        try {
            return new ResponseEntity<>(resourcesService.getRessourceBySession(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les ressources n'ont pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @GetMapping("/getAllCourseResourse")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllCourseResourse() {
        try {
            return new ResponseEntity<>(courseResourceService.getAllCourseResourse(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("les ressources n'ont pas pu être récupérée", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/addCourseResource")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addCourseResource(@RequestBody CoursResourceDTO coursResourceDTO) {
        try {
            CourseResourse courseResourse=courseResourceService.save(coursResourceDTO);
            return new ResponseEntity<>(courseResourse, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
           System.out.println(e);
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/delCourseResource")
    public ResponseEntity<Object> deleteCourseResource(@RequestParam(value = "id")  Long id) {
        try {
            courseResourceService.deleteById(id);
            ;
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("l'intention n'a pas pu être supprimé", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}

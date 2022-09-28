package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uqtr.ecompagnon.service.implement.RegionServiceImpl;

@RestController
@RequestMapping("/api/Region")
@AllArgsConstructor
public class RegionController {

    private final RegionServiceImpl regionService;

    @GetMapping("/getAllRegion")
    // @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINREAD')")
    //  @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> getAllRegion() {
        try {
            return new ResponseEntity<>(regionService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste n'a pas pu être récupérée"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}

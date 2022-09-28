package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uqtr.ecompagnon.dto.LoginResponseDTO;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.service.implement.AppUserServiceImpl;

@RestController
@RequestMapping("/api/User")
@AllArgsConstructor
public class AppUserController {
    private final AppUserServiceImpl appUserService;

    @GetMapping("/getUserById")
    public ResponseEntity<Object> getUserById(@RequestParam(value = "id") long user) {
        try {
            return new ResponseEntity<>(appUserService.getUserById(user), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("l'utilisateur n'a pas pu être récupéré", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllusers")
    public ResponseEntity<Object> getAllusers() {
        try {
            return new ResponseEntity<>(appUserService.findAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("la liste des utilisateurs n'a pas pu être récupéré"+e, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/verifyFinishUser")
    public ResponseEntity<Object> verifyFinishUser(@RequestParam(value = "cpUser") String cpUser) {
        try {
            return new ResponseEntity<>(appUserService.verifyFinishUser(cpUser), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("l'utilisateur n'a pas pu être récupéré", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getUserByCP")
    public ResponseEntity<Object> getUserByCP(@RequestParam(value = "CP") String CP) {
        try {

            AppUser appUser= (AppUser) appUserService.loadUserByUserCP(CP);

            LoginResponseDTO loginResponseDTO= new LoginResponseDTO(appUser.getIdUsers(),
                                                                    appUser.getLastname(),
                                                                    appUser.getFirstname(),
                                                                    appUser.getMailUsers(),
                                                                    appUser.getCPUsers(),
                                                                    appUser.getFormField(),
                                                                    appUser.getTokenNotific());

            return new ResponseEntity<>(loginResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity("l'utilisateur n'a pas pu être récupéré", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

}

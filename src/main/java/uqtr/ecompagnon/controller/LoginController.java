package uqtr.ecompagnon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.Users;
import uqtr.ecompagnon.dto.LoginRequestDTO;
import uqtr.ecompagnon.dto.LoginResponseDTO;
import uqtr.ecompagnon.service.implement.AppUserServiceImpl;
import uqtr.ecompagnon.service.implement.LoginServiceImpl;
import uqtr.ecompagnon.service.implement.CpMailServiceImpl;
import uqtr.ecompagnon.service.implement.UsersServiceImpl;
import uqtr.ecompagnon.util.exception.AppRequestException;

@RestController
@RequestMapping("api/login")
@AllArgsConstructor
public class LoginController {

    private final LoginServiceImpl loginServiceImpl;
    private final CpMailServiceImpl cp_mailService;
    private AuthenticationManager authenticationManager;
    private final AppUserServiceImpl appUserServiceImpl;
    private final UsersServiceImpl usersService;


    @PostMapping(value ="/etudiant")
    public ResponseEntity<Object> loginEtud(@RequestBody LoginRequestDTO request)
            throws Exception {
        String email=request.getMailUsers();
        email=email.toLowerCase();
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,
                            request.getPasswordUsers())
            );
        }
       catch (BadCredentialsException e) {

          throw new AppRequestException("BAD_CREDENTIAL");
           // throw new BadCredentialsException("Incorrect username or password", e);
        }
        catch (DisabledException e) {
            System.out.println("error");
            throw new AppRequestException("ACCOUNT_DISABLED");
            // throw new Exception("USER_DISABLED", e);
        }


        String token= loginServiceImpl.login(request);
        AppUser appUser= (AppUser) appUserServiceImpl.loadUserByUsername(email);
       LoginResponseDTO loginRespons=new LoginResponseDTO(appUser.getIdUsers(),
                                                    appUser.getLastname(),
                                                    appUser.getFirstname(),
                                                    appUser.getMailUsers(),
                                                    appUser.getCPUsers(),
                                                    appUser.getFormField(),
                                                    token,
                                                    appUser.getTokenNotific()
                                                   );
        //return ResponseEntity.ok(new JwtResponse(token));
        return new ResponseEntity(loginRespons, HttpStatus.OK);

      //  return loginService.login(request);
    }

    @PostMapping(value ="/admin")
    public ResponseEntity<Object> loginAdmin(@RequestBody LoginRequestDTO request)
            throws Exception {
        String email=request.getMailUsers();
        email=email.toLowerCase();
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email,
                            request.getPasswordUsers())
            );
        }
        catch (BadCredentialsException e) {

            throw new AppRequestException("BAD_CREDENTIAL");
            // throw new BadCredentialsException("Incorrect username or password", e);
        }
        catch (DisabledException e) {
            System.out.println("error");
            throw new AppRequestException("ACCOUNT_DISABLED");
            // throw new Exception("USER_DISABLED", e);
        }


        String token= loginServiceImpl.login(request);
      // Users users= (Users) usersService.loadUserByUsername(email);

        //return ResponseEntity.ok(new JwtResponse(token));
        return new ResponseEntity("", HttpStatus.OK);

        //  return loginService.login(request);
    }

}

package uqtr.ecompagnon.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uqtr.ecompagnon.model.Account;
import uqtr.ecompagnon.service.implement.*;

@RestController
@RequestMapping("/Account")
@AllArgsConstructor
//@SecurityRequirement(name = "ecompagnon-api")
public class AccountController {
    private final SimpleMailMessage template;
    private final UtilServiceImpl utilServiceImpl;
    private final AccountServiceImpl accountServiceImpl;
    private final uqtr.ecompagnon.service.implement.UsersServiceImpl UsersServiceImpl;
    private final ProfileServiceImpl profileServiceImpl;
    private final CpMailServiceImpl cp_MailService;


   /* @Autowired
    AuthenticationManager authenticationManager;*/


    @PostMapping("/addAccountEtud")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> addNewAccounts(@RequestBody Account account) {
        try {
      /*      if (UsersServiceImpl.existsUsersByMailUsersAndAndCPUsers(account.getAccUser().getMailUsers(), account.getAccUser().getCPUsers())) {
                return new ResponseEntity(1, HttpStatus.OK);
            }

            if ((cp_MailService.existsCpMailByMailAndCp(account.getAccUser().getMailUsers(), account.getAccUser().getCPUsers())) == false) {
                return new ResponseEntity(2, HttpStatus.OK);
            }*/

            System.out.println(account.toString());
            account.setAccProfile(profileServiceImpl.getProfileByNom("Etudiant"));
            // accountService.save(account);
            String text = String.format(template.getText());
            String subject = "Confirmer votre inscription sur ECompagnon";
            String to = account.getAccUser().getMailUsers();
            //utilServiceImpl.sendMailGmail(to, text,subject);

            return new ResponseEntity(account, HttpStatus.OK);

        } catch (Exception e) {
            // TODO: handle exception
            // Accounts.out.println(Accounts.toString());
            return new ResponseEntity("Erreur d'enregistrement du Accounts", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

   /* @PostMapping("/loginAccount")
    // @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> loginAccount(@RequestBody UserCredentialDTO userCredentialDTO) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userCredentialDTO.getMailUsers(), userCredentialDTO.getPasswordUsers()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));



    }
*/
}

package uqtr.ecompagnon.service.implement;

import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.dto.LoginRequestDTO;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.Connected;
import uqtr.ecompagnon.repository.AppUserRepository;
import uqtr.ecompagnon.jwt.JwtConfig;
import uqtr.ecompagnon.repository.ConnectedRepository;
import uqtr.ecompagnon.service.LoginService;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;
    private final AppUserServiceImpl appUserServiceImpl;
    private final ConnectedRepository connectedRepository;
    private final JwtConfig jwt;
@Override
    public String login(LoginRequestDTO request){
    String email=request.getMailUsers();
     email=email.toLowerCase();
    AppUser appUser= (AppUser) appUserServiceImpl.loadUserByUsername(email);

    //Connected connected=new Connected(0,appUser, LocalDateTime.now(),null);
   // connectedRepository.save(connected);

    String token=jwtConfig.getTokenPrefix()+genrerToken(request);


   return token;

    }

@Override
    public String genrerToken(LoginRequestDTO request){
    String email=request.getMailUsers();
    email=email.toLowerCase();
        String token = Jwts.builder()
                .setSubject(email)
                //.claim("authorities", request.getAuthorities())
                .setIssuedAt(new java.util.Date())
               // .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();

 return token;


    }

}

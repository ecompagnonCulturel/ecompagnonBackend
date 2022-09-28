package uqtr.ecompagnon.service.implement;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import uqtr.ecompagnon.dto.UserDTO;
import uqtr.ecompagnon.model.AppUser;
import uqtr.ecompagnon.model.AppUserRole;
import uqtr.ecompagnon.model.Users;
import uqtr.ecompagnon.repository.UsersRepository;
import uqtr.ecompagnon.service.UsersService;
import uqtr.ecompagnon.util.exception.AppRequestException;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Transactional
@Service("adminAuthenticator")
public class UsersServiceImpl implements UsersService{

    private final  UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public <S extends Users> S save(UserDTO userDTO) {
        List<Users> userss=findByNameUsers(userDTO.getAccName());

        if (userss.size()<=1)
        {
            Users users=new Users();
            users.setIdUsers(userDTO.getId());
            users.setNameUsers(userDTO.getAccName());
            users.setMailUsers(userDTO.getAccName());
            users.setCPUsers(userDTO.getAccName());
            users.setStatusUsers(1L);
            users.setDateModifUsers(new Date());
            users.setPasswordUsers(passwordEncoder.encode(userDTO.getAccPassword()));
            return (S)usersRepository.save(users);
        }
        else
        {
            throw new AppRequestException("Ce nom d'utilisateur existe déjà");
        }


    }
@Override
   public List<Users> findByNameUsers(String nameUser)
    {

        return this.usersRepository.findByNameUsers(nameUser);
    }




}

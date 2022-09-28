package uqtr.ecompagnon.service;

import uqtr.ecompagnon.dto.UserDTO;
import uqtr.ecompagnon.model.Users;
import java.util.List;

public interface UsersService {
   List<Users>  findByNameUsers(String nameUser);
   <S extends Users> S save(UserDTO request);
}

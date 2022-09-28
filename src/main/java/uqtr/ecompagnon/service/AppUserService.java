package uqtr.ecompagnon.service;

import uqtr.ecompagnon.dto.AppUserUpdatePwdDTO;
import uqtr.ecompagnon.dto.UserCredentialDTO;
import uqtr.ecompagnon.dto.UserListDTO;
import uqtr.ecompagnon.model.AppUser;

import java.util.List;

public interface AppUserService {

    public String signUpUser(AppUser appUser);
    public AppUser updateUpUser(AppUser appUser);
    public AppUser updateUpUserPwd(AppUserUpdatePwdDTO appUserUpdatePwdDTO);
    public AppUser initPwdAppUser(AppUser appUser,String passWord);
    public void enableAppUser(String email);
    public void initPwdAppUserNoToken(UserCredentialDTO userCredentialDTO);
    public  AppUser verifyFinishUser(String cpUser);
    List<Object> findAllUsers();

}

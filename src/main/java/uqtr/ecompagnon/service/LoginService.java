package uqtr.ecompagnon.service;

import uqtr.ecompagnon.dto.LoginRequestDTO;

public interface LoginService {
    String login(LoginRequestDTO request);
    String genrerToken(LoginRequestDTO request);
}

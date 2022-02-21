package org.squad3.library.gateway.service.impl;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.squad3.library.gateway.exception.Exception;
import org.squad3.library.gateway.mapper.UserMappingHelper;
import org.squad3.library.gateway.model.dtos.AccountDTO;
import org.squad3.library.gateway.model.dtos.UserDTO;
import org.squad3.library.gateway.model.request.RegisterReqDTO;
import org.squad3.library.gateway.model.response.LoginResDTO;
import org.squad3.library.gateway.model.response.SystemResponse;
import org.squad3.library.gateway.proxies.UserServiceProxy;
import org.squad3.library.gateway.service.AuthenticationService;
import org.squad3.library.gateway.service.JwtService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserServiceProxy userServiceProxy;

    @Value("${app.token.role.reader}")
    private String ROLE_TOKEN_READER;

    private String PASSWORD_INVALID= "Password invalid";

    @Override
    public UserDTO register(RegisterReqDTO registerReqDTO) {
        try {
            String encryptedPassword = passwordEncoder.encode(registerReqDTO.getAccount().getPassword());
            UserDTO userDTO = new UserDTO(registerReqDTO, encryptedPassword, ROLE_TOKEN_READER);
            SystemResponse userServiceRes = this.userServiceProxy.createUser(userDTO);
            return this.buildUserDTORes(userServiceRes);
        }catch (FeignException e){
            throw new Exception(HttpStatus.valueOf(e.status()), e.getMessage());
        }
    }

    @Override
    public LoginResDTO login(AccountDTO accountDTO){
        try {
            SystemResponse systemResponse = this.userServiceProxy.getUserByLoginId(accountDTO.getUsername());
            UserDTO userDTO = UserMappingHelper.convertToUserDTO(systemResponse.getData());
            if (passwordEncoder.matches(accountDTO.getPassword(), userDTO.getAccount().getPassword())) {
                return this.buildLoginResDTO(userDTO);
            }else throw new Exception(HttpStatus.BAD_REQUEST, PASSWORD_INVALID);

        } catch (FeignException e) {
            throw new Exception(HttpStatus.valueOf(e.status()), e.getMessage() );
        }
    }

    private LoginResDTO buildLoginResDTO(UserDTO userDTO) {
        String accessToken = this.jwtService.generateAccessToken(userDTO);
        String refreshToken = this.jwtService.generateRefreshToken(userDTO.getAccount());
        return new LoginResDTO(accessToken, refreshToken);
    }

    private UserDTO buildUserDTORes(SystemResponse systemResponse) {
        UserDTO userRes = UserMappingHelper.convertToUserDTO(systemResponse.getData());
        userRes.getAccount().setPassword(null);
        userRes.getAccount().setUsername(ROLE_TOKEN_READER);
        return userRes;
    }

}

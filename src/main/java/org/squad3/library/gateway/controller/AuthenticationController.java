package org.squad3.library.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.squad3.library.gateway.model.dtos.AccountDTO;
import org.squad3.library.gateway.model.dtos.UserDTO;
import org.squad3.library.gateway.model.request.RegisterReqDTO;
import org.squad3.library.gateway.model.response.LoginResDTO;
import org.squad3.library.gateway.model.response.SystemResponse;
import org.squad3.library.gateway.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1/authentication/")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "register")
    public ResponseEntity<SystemResponse> register(@RequestBody RegisterReqDTO registerReqDTO) {
        UserDTO userDTO = authenticationService.register(registerReqDTO);
        SystemResponse systemResponse = SystemResponse.builder()
                .code(String.valueOf(HttpStatus.CREATED.value()))
                .status(HttpStatus.OK.getReasonPhrase())
                .data(userDTO).build();
        return new ResponseEntity<>(systemResponse, HttpStatus.CREATED);
    }

    @PostMapping(value = "login")
    public ResponseEntity<SystemResponse> login(@RequestBody AccountDTO accountDTO) {
        LoginResDTO loginResDTO = authenticationService.login(accountDTO);
        SystemResponse systemResponse = SystemResponse.builder()
                .code(String.valueOf(HttpStatus.OK.value()))
                .status(HttpStatus.OK.getReasonPhrase())
                .data(loginResDTO).build();
        return ResponseEntity.ok(systemResponse);
    }
}

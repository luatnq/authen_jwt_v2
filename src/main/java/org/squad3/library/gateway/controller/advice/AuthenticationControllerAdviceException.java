package org.squad3.library.gateway.controller.advice;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.squad3.library.gateway.exception.Exception;
import org.squad3.library.gateway.model.response.SystemResponse;

@RestControllerAdvice
@Log4j2
public class AuthenticationControllerAdviceException {
    @ExceptionHandler(value = {Exception.class})
    ResponseEntity<SystemResponse> handleRestTemplateException(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        SystemResponse response = SystemResponse.builder()
                .status(String.valueOf(ex.getStatusCode()))
                .message(ex.getMessage())
                .code(String.valueOf(ex.getStatusCode()))
                .build();
        return new ResponseEntity<>(response, ex.getStatusCode());
    }
}

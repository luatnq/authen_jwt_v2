package org.squad3.library.gateway.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class Exception extends RuntimeException{
    private HttpStatus statusCode;
    private String error;
    private String message;

    public Exception(HttpStatus statusCode, String message){
        super();
        this.statusCode = statusCode;
        this.message = message;
    }
}

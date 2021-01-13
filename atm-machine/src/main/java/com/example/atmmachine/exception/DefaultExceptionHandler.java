package com.example.atmmachine.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(InvalidAmount.class)
    @ResponseBody
    ResponseEntity<Error> handleInvalidAmount(Exception e) {
        return new ResponseEntity<Error>(
                Error.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .errorCode("ERR001")
                .errorDesc("Invalid Amount. Please enter the amount the the multiples of 10, 20, 50 and 100s.")
                .timestamp(new Date()).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidPIN.class)
    @ResponseBody
    ResponseEntity<Error> handleInvalidPIN(Exception e) {

        return new ResponseEntity<Error>(
                Error.builder()
                        .status(HttpStatus.UNAUTHORIZED.value())
                        .errorCode("ERR002")
                        .errorDesc("Invalid PIN. Please enter correct PIN")
                        .timestamp(new Date()).build(), HttpStatus.UNAUTHORIZED);

    }

    @Data
    @Builder
    static class Error {
        int status;
        String errorCode;
        String errorDesc;
        Date timestamp;
    }
}

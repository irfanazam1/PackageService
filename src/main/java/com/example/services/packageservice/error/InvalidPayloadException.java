package com.example.services.packageservice.error;


import com.example.services.packageservice.model.ErrorDto;
import org.springframework.http.HttpStatus;

public class InvalidPayloadException extends HttpStatusException{
    private String errorCode = "E-2";
    public InvalidPayloadException(String params){
        error = new ErrorDto(errorCode, String.format("Server couldn't understand the value of (%s) in the payload provided.", params), HttpStatus.BAD_REQUEST);
    }
}

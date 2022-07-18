package com.example.services.packageservice.error;

import com.example.services.packageservice.model.ErrorDto;
import org.springframework.http.HttpStatus;

public class PackageServiceException extends HttpStatusException{
    private String errorCode = "E-4";
    public PackageServiceException(String message){
        error = new ErrorDto(errorCode, String.format("Exception Occurred: %s", message), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

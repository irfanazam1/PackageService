package com.example.services.packageservice.error;


import com.example.services.packageservice.model.ErrorDto;
import org.springframework.http.HttpStatus;

public class DuplicatePackageNameException extends HttpStatusException{
    private String errorCode = "E-5";
    public DuplicatePackageNameException(){
        error = new ErrorDto(errorCode,  "A package with the same name already exists.", HttpStatus.BAD_REQUEST);
    }
}

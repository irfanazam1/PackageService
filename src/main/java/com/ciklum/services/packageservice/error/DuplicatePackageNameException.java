package com.ciklum.services.packageservice.error;


import com.ciklum.services.packageservice.model.ErrorDto;
import org.springframework.http.HttpStatus;

public class DuplicatePackageNameException extends HttpStatusException{
    private String errorCode = "E-5";
    public DuplicatePackageNameException(){
        error = new ErrorDto(errorCode,  "A package with the same name already exists.", HttpStatus.BAD_REQUEST);
    }
}

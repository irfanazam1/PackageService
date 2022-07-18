package com.example.services.packageservice.error;

import com.example.services.packageservice.model.ErrorDto;
import org.springframework.http.HttpStatus;

public class PackageNotFoundException extends HttpStatusException {
    private String erroCode = "E-3";
    public PackageNotFoundException(int id){
        error = new ErrorDto(erroCode, String.format("No package found with the given id: %s", id), HttpStatus.NOT_FOUND);
    }

}

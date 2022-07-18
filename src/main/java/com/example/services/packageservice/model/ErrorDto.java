package com.example.services.packageservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
/**
 * Class to store the error info.
 */
public class ErrorDto {
    private String errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;
}

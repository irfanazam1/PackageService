package com.ciklum.services.packageservice.error;

import com.ciklum.services.packageservice.model.ErrorDto;
import org.springframework.http.HttpStatus;

public class NoContentException extends HttpStatusException{
    private static final String errorCode = "E-1";
    public NoContentException(String resource) {
        error = new ErrorDto(errorCode, String.format("No content found for resource: (%s)", resource), HttpStatus.NO_CONTENT);
    }
}

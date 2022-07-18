package com.example.services.packageservice.error;

import com.example.services.packageservice.model.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpStatusException.class)
    public ResponseEntity<ErrorDto> httpStatusException(HttpStatusException exception){
        log.error("{}", exception);
        return new ResponseEntity<>(exception.getError(), exception.getError().getHttpStatus());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> unknownException(Exception exception){
        log.error("{}", exception);
        PackageServiceException unknownException = new PackageServiceException(exception.getMessage());
        return new ResponseEntity<>(unknownException.getError(), unknownException.getError().getHttpStatus());
    }

}

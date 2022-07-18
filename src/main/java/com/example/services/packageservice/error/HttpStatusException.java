package com.example.services.packageservice.error;

import com.example.services.packageservice.model.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpStatusException extends RuntimeException {
    protected ErrorDto error;
}

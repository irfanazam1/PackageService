package com.ciklum.services.packageservice.error;

import com.ciklum.services.packageservice.model.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpStatusException extends RuntimeException {
    protected ErrorDto error;
}

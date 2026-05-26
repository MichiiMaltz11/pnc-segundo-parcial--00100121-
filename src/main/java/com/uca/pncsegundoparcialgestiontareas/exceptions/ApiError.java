package com.uca.pncsegundoparcialgestiontareas.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ApiError {
    private String message;
    private int code;
    private LocalDate timestamp;
    private Object errors;
}

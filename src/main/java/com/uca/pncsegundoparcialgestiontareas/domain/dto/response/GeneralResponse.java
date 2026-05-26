package com.uca.pncsegundoparcialgestiontareas.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GeneralResponse {
    private Object data;
    private String message;
}


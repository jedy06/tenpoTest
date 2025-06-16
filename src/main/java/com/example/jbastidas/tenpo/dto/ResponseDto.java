package com.example.jbastidas.tenpo.dto;

import java.math.BigDecimal;

public record ResponseDto(
        BigDecimal numero1,
        BigDecimal numero2,
        BigDecimal porcentajeExterno,
        BigDecimal resultadoCalculo
) {
}

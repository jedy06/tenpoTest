package com.example.jbastidas.tenpo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class CalculatorReqDto {

    private BigDecimal numero1;
    private BigDecimal numero2;

}

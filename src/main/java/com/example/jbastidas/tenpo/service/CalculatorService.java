package com.example.jbastidas.tenpo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
public class CalculatorService {


    public BigDecimal percentResult(BigDecimal num1, BigDecimal num2, BigDecimal percent){

        BigDecimal sumRes = num1.add(num2);
        BigDecimal percentRes = percent.divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_UP);
        BigDecimal totalPercent = sumRes.multiply(percentRes);
        log.info("resultado del calculo porcentual: " + totalPercent);
        return totalPercent;
    }
}

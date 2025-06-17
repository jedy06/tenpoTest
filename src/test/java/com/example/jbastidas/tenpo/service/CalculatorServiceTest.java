package com.example.jbastidas.tenpo.service;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    void testPercentResult_returnCorrectPercentage() {
        // Arrange
        BigDecimal num1 = new BigDecimal("10");
        BigDecimal num2 = new BigDecimal("20");
        BigDecimal percent = new BigDecimal("10");

        // Act
        BigDecimal result = calculatorService.percentResult(num1, num2, percent);

        // Assert
        assertEquals(new BigDecimal("3.000"), result);
    }

    @Test
    void testPercentResult_zeroPercent() {
        BigDecimal result = calculatorService.percentResult(
                new BigDecimal("10"), new BigDecimal("5"), BigDecimal.ZERO
        );
        assertEquals(BigDecimal.ZERO.setScale(3), result);
    }

    @Test
    void testPercentResult_negativeValues_shouldWorkCorrectly() {
        BigDecimal result = calculatorService.percentResult(
                new BigDecimal("-5"), new BigDecimal("5"), new BigDecimal("50")
        );
        assertEquals(BigDecimal.ZERO.setScale(3), result);
    }
}

package com.microservices.calculator.business_controllers;

import com.microservices.calculator.dtos.OperationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OperationBusinessControllerTest {
    @Autowired
    private OperationBusinessController operationBusinessController;

    OperationDto createOperationDto (String firstNumber, String secondNumber, String operator) {
        return new OperationDto(new BigDecimal(firstNumber), new BigDecimal(secondNumber), operator);
    }

    @Test
    void testAdd() {
        assertEquals(new BigDecimal("9.5"), operationBusinessController.calculate(createOperationDto("5", "4.5", "add")).getResult());
    }

    @Test
    void testAddNegativeNumbers() {
        assertEquals(new BigDecimal("-9.5"), operationBusinessController.calculate(createOperationDto("-5", "-4.5", "add")).getResult());
    }

    @Test
    void testSubtract() {
        assertEquals(new BigDecimal("0.5"), operationBusinessController.calculate(createOperationDto("5", "4.5", "subtract")).getResult());
    }

    @Test
    void testSubtractNegativeNumbersOk() {
        assertEquals(new BigDecimal("-0.5"), operationBusinessController.calculate(createOperationDto("-5", "-4.5", "subtract")).getResult());
    }
}

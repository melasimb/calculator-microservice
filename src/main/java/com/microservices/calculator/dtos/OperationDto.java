package com.microservices.calculator.dtos;

import com.microservices.calculator.exceptions.BadRequestException;

import java.math.BigDecimal;

public class OperationDto {
    private BigDecimal firstNumber;

    private BigDecimal secondNumber;

    private String operator;

    public OperationDto(BigDecimal firstNumber, BigDecimal secondNumber, String operator) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
    }

    public BigDecimal getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(BigDecimal firstNumber) {
        this.firstNumber = firstNumber;
    }

    public BigDecimal getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(BigDecimal secondNumber) {
        this.secondNumber = secondNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void validate() {
        if (operator.isEmpty()) {
            throw new BadRequestException("Incomplete query params. ");
        } else if (!operator.equals("add") && !operator.equals("subtract")) {
            throw new BadRequestException("operator is incorrect, valid operators are + and -");
        }
    }

    @Override
    public String toString() {
        return "OperationDto{" +
                "firstNumber=" + firstNumber +
                ", secondNumber=" + secondNumber +
                ", operator='" + operator + '\'' +
                '}';
    }
}

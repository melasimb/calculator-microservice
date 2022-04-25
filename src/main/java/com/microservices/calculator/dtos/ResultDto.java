package com.microservices.calculator.dtos;

import java.math.BigDecimal;

public class ResultDto {
    private BigDecimal result;

    public ResultDto(BigDecimal result) {
        this.result = result;
    }

    public ResultDto() {
        // Empty for framework
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "result=" + result +
                '}';
    }
}

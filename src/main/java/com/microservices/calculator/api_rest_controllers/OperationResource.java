package com.microservices.calculator.api_rest_controllers;

import com.microservices.calculator.business_controllers.OperationBusinessController;
import com.microservices.calculator.dtos.OperationDto;
import com.microservices.calculator.dtos.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(OperationResource.OPERATIONS)
public class OperationResource {
    static final String OPERATIONS = "/operations";
    static final String CALCULATE = "/calculate";

    private OperationBusinessController operationBusinessController;

    @Autowired
    public OperationResource(OperationBusinessController operationBusinessController) {
        this.operationBusinessController = operationBusinessController;
    }

    @GetMapping(value = CALCULATE)
    public ResultDto calculate(@RequestParam BigDecimal firstNumber, @RequestParam BigDecimal secondNumber, @RequestParam String operator) {
        OperationDto operationDto = new OperationDto(firstNumber, secondNumber, operator);
        operationDto.validate();
        return this.operationBusinessController.calculate(operationDto);
    }
}

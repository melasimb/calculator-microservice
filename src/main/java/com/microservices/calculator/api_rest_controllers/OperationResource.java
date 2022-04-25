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
    static final String ADD = "/add";
    static final String SUBTRACT = "/subtract";

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

    @GetMapping(value = ADD)
    public ResultDto add(@RequestParam BigDecimal firstNumber, @RequestParam BigDecimal secondNumber) {
        OperationDto operationDto = new OperationDto(firstNumber, secondNumber, "add");
        return this.operationBusinessController.calculate(operationDto);
    }

    @GetMapping(value = SUBTRACT)
    public ResultDto subtract(@RequestParam BigDecimal firstNumber, @RequestParam BigDecimal secondNumber) {
        OperationDto operationDto = new OperationDto(firstNumber, secondNumber, "subtract");
        return this.operationBusinessController.calculate(operationDto);
    }
}

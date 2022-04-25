package com.microservices.calculator.business_controllers;

import com.microservices.calculator.dtos.OperationDto;
import com.microservices.calculator.dtos.ResultDto;
import org.springframework.stereotype.Controller;

@Controller
public class OperationBusinessController {
    public ResultDto calculate(OperationDto operationDto) {
        ResultDto resultDto = new ResultDto();
        switch (operationDto.getOperator()) {
            case "add":
                resultDto.setResult(operationDto.getFirstNumber().add(operationDto.getSecondNumber()));
                break;
            case "subtract":
                resultDto.setResult(operationDto.getFirstNumber().subtract(operationDto.getSecondNumber()));
                break;
        }
        return resultDto;
    }
}

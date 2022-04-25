package com.microservices.calculator.api_rest_controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.calculator.business_controllers.OperationBusinessController;
import com.microservices.calculator.dtos.OperationDto;
import com.microservices.calculator.dtos.ResultDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OperationResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OperationBusinessController operationBusinessController;

    @Autowired
    ObjectMapper objectmapper;

    @Test
    void testAdd() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("4"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get(OperationResource.OPERATIONS + OperationResource.ADD).queryParam("firstNumber", "2").queryParam("secondNumber", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(4)));
    }

    @Test
    void testSubtract() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("-4"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get(OperationResource.OPERATIONS + OperationResource.SUBTRACT).queryParam("firstNumber", "-2").queryParam("secondNumber", "-2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(-4)));
    }

    @Test
    void testAddIncorrectTypeQueryParam() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("4"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get(OperationResource.OPERATIONS + OperationResource.ADD)
                        .queryParam("firstNumber", "two")
                        .queryParam("secondNumber", "2"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAddIncompleteQueryParam() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("4"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get(OperationResource.OPERATIONS + OperationResource.ADD)
                        .queryParam("firstNumber", "two"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSubtractIncorrectTypeQueryParam() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("0"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get(OperationResource.OPERATIONS + OperationResource.SUBTRACT)
                        .queryParam("firstNumber", "two")
                        .queryParam("secondNumber", "2"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSubtractIncompleteQueryParam() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("0"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get(OperationResource.OPERATIONS + OperationResource.SUBTRACT)
                        .queryParam("firstNumber", "two"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCalculateAdd() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("4"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                        .queryParam("firstNumber", "2")
                        .queryParam("secondNumber", "2")
                        .queryParam("operator", "add"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(4)));
    }

    @Test
    void testCalculateSubtract() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("-2"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                        .queryParam("firstNumber", "2")
                        .queryParam("secondNumber", "-4")
                        .queryParam("operator", "subtract"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(-2)));
    }

    @Test
    void testCalculateIncorrectOperator() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("0"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                        .queryParam("firstNumber", "2")
                        .queryParam("secondNumber", "2")
                        .queryParam("operator", "-"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCalculateEmptyOperator() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("0"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                        .queryParam("firstNumber", "2")
                        .queryParam("secondNumber", "2")
                        .queryParam("operator", ""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCalculateIncorrectTypeQueryParam() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("0"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                        .queryParam("firstNumber", "two")
                        .queryParam("secondNumber", "2")
                        .queryParam("operator", "add"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCalculateIncompleteQueryParam() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("0"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                        .queryParam("firstNumber", ""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCalculateIncorrectRequest() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("6"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        this.mockMvc
                .perform(get("/operation" + OperationResource.CALCULATE)
                        .queryParam("firstNumber", "3")
                        .queryParam("secondNumber", "3")
                        .queryParam("operator", "add"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void testOperationOperatorAdd() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("6.5"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        OperationDto operationDto = new OperationDto(new BigDecimal(3), new BigDecimal(3.5), "add");

        this.mockMvc
                .perform(post(OperationResource.OPERATIONS)
                        .content(objectmapper.writeValueAsString(operationDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(6.5)));
    }

    @Test
    void testOperationOperatorSubtract() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("-0.5"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        OperationDto operationDto = new OperationDto(new BigDecimal(3), new BigDecimal(3.5), "subtract");

        this.mockMvc
                .perform(post(OperationResource.OPERATIONS)
                        .content(objectmapper.writeValueAsString(operationDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(-0.5)));
    }

    @Test
    void testOperationOperatorIncorrect() throws Exception {

        ResultDto resultDto = new ResultDto(new BigDecimal("9"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        OperationDto operationDto = new OperationDto( new BigDecimal(5), new BigDecimal(4), "addd");

        this.mockMvc
                .perform(post(OperationResource.OPERATIONS)
                        .content(objectmapper.writeValueAsString(operationDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void testOperationDtoIncorrect() throws Exception {
        ResultDto resultDto = new ResultDto(new BigDecimal("9"));
        when(operationBusinessController.calculate(any(OperationDto.class))).thenReturn(resultDto);

        OperationDto operationDto = new OperationDto( new BigDecimal(5), null, "subtract");

        this.mockMvc
                .perform(post(OperationResource.OPERATIONS)
                        .content(objectmapper.writeValueAsString(operationDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}

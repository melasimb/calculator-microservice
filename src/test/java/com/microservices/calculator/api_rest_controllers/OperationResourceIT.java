package com.microservices.calculator.api_rest_controllers;

import com.microservices.calculator.ApiTestConfig;
import com.microservices.calculator.dtos.ResultDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ApiTestConfig
public class OperationResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCalculateAdd() {
        ResultDto resultDto = this.webTestClient
                .get().uri(uriBuilder ->
                        uriBuilder.path(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                                .queryParam("firstNumber", 2)
                                .queryParam("secondNumber", 3)
                                .queryParam("operator", "add")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResultDto.class)
                .returnResult().getResponseBody();
        assertEquals(new BigDecimal(5), resultDto.getResult());
    }

    @Test
    void testCalculateSubtract() {
        ResultDto resultDto = this.webTestClient
                .get().uri(uriBuilder ->
                        uriBuilder.path(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                                .queryParam("firstNumber", 3)
                                .queryParam("secondNumber", 5)
                                .queryParam("operator", "subtract")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResultDto.class)
                .returnResult().getResponseBody();
        assertEquals(new BigDecimal(-2), resultDto.getResult());
    }

    @Test
    void testCalculateIncorrectOperator() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder.path(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                .queryParam("firstNumber", 3)
                .queryParam("secondNumber", 5)
                .queryParam("operator", "-")
                .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testCalculateEmptyOperator() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder.path(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                .queryParam("firstNumber", 3)
                .queryParam("secondNumber", 5)
                .queryParam("operator", "")
                .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testCalculateIncorrectTypeQueryParam() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder.path(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                .queryParam("firstNumber", "three")
                .queryParam("secondNumber", 5)
                .queryParam("operator", "add")
                .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
        void testCalculateIncompleteQueryParam() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder.path(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                .queryParam("firstNumber", "")
                .queryParam("secondNumber", "")
                .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testCalculateIncorrectRequest() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder.path("/operation" + OperationResource.CALCULATE)
                .queryParam("firstNumber", 3)
                .queryParam("secondNumber", 5)
                .queryParam("operator", "add")
                .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testAdd() {
        ResultDto resultDto = this.webTestClient
                .get().uri(uriBuilder ->
                        uriBuilder.path(OperationResource.OPERATIONS + OperationResource.ADD)
                                .queryParam("firstNumber", 2.5)
                                .queryParam("secondNumber", 3)
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResultDto.class)
                .returnResult().getResponseBody();
        assertEquals(new BigDecimal(5.5), resultDto.getResult());
    }

    @Test
    void testSubtract() {
        ResultDto resultDto = this.webTestClient
                .get().uri(uriBuilder ->
                        uriBuilder.path(OperationResource.OPERATIONS + OperationResource.SUBTRACT)
                                .queryParam("firstNumber", 2.5)
                                .queryParam("secondNumber", 3)
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResultDto.class)
                .returnResult().getResponseBody();
        assertEquals(new BigDecimal(-0.5), resultDto.getResult());
    }

    @Test
    void testAddIncorrectTypeQueryParam() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder.path(OperationResource.OPERATIONS + OperationResource.ADD)
                .queryParam("firstNumber", "three")
                .queryParam("secondNumber", 5)
                .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testAddIncompleteQueryParam() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder.path(OperationResource.OPERATIONS + OperationResource.CALCULATE)
                .queryParam("firstNumber", 5)
                .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testSubtractIncorrectTypeQueryParam() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder.path(OperationResource.OPERATIONS + OperationResource.SUBTRACT)
                .queryParam("firstNumber", "three")
                .queryParam("secondNumber", 5)
                .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testSubtractIncompleteQueryParam() {
        this.webTestClient
                .get().uri(uriBuilder -> uriBuilder.path(OperationResource.OPERATIONS + OperationResource.SUBTRACT)
                .queryParam("firstNumber", 5)
                .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
}

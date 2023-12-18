package com.devops.devopsexamt1.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.devops.devopsexamt1.dtos.DoMathRequestDto;
import com.devops.devopsexamt1.payload.ApiResponse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MathControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void doMathOperation_Success(){
        DoMathRequestDto dto = new DoMathRequestDto(2, 5, "+");
        DoMathRequestDto dto1 = new DoMathRequestDto(200, 50, "*");
        DoMathRequestDto dto2 = new DoMathRequestDto(5, 10, "/");
        DoMathRequestDto dto3 = new DoMathRequestDto(34, 54, "-");
        ResponseEntity<ApiResponse> response = this.    restTemplate.postForEntity("/api/v1/do_math",dto,ApiResponse.class);

        assertEquals(200, response.getStatusCode().value());
    }
}
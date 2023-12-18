package com.devops.devopsexamt1.controllers;

import com.devops.devopsexamt1.dtos.DoMathRequestDto;
import com.devops.devopsexamt1.exceptions.InvalidOperationException;
import com.devops.devopsexamt1.serviceImpls.MathOperatorImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
public class MathControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MathOperatorImpl mathOperatorMock;

    @BeforeEach
    public void setUp() throws InvalidOperationException {
        when(mathOperatorMock.doMath(1, 2, "+")).thenReturn(3.0);
        when(mathOperatorMock.doMath(4, 0, "/")).thenThrow(new InvalidOperationException("Cannot divide by zero"));
        when(mathOperatorMock.doMath(6, 6, "&")).thenThrow(new RuntimeException("Unknown operation"));
    }

    @Test
    void shouldReturnOperationResult() throws InvalidOperationException {
        assertEquals(3.0, mathOperatorMock.doMath(1, 2, "+"));
    }

    @Test
    void givenANumberWhenDividedByZeroItThrowAnError() {
        assertThrows(InvalidOperationException.class, () -> mathOperatorMock.doMath(4, 0, "/"));
    }

    @Test
    void givenAnUnknownOperatorWhenUsedItThrowsRuntimeException() {
        assertThrows(RuntimeException.class, () -> mathOperatorMock.doMath(6, 6, "&"));
    }

    @Test
    public void shouldReturnCalcResponse() throws Exception {
        when(mathOperatorMock.doMath(2.0, 3.0, "+")).thenReturn(5.0);
        DoMathRequestDto doMathRequest = new DoMathRequestDto(2, 3, "+");

        mockMvc.perform(post("/api/v1/math-operator/do-math")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(doMathRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.calcResponse").value(5.0));
    }

    @Test
    public void shouldReturnNotAcceptable() throws Exception {
        DoMathRequestDto doMathRequest = new DoMathRequestDto(4, 0, "/");
        mockMvc.perform(post("/api/v1/math-operator/do-math")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(doMathRequest)))
                .andExpect(status().isNotAcceptable());
    }
}

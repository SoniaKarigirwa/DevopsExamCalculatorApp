package com.devops.devopsexamt1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devops.devopsexamt1.dtos.DoMathRequestDto;
import com.devops.devopsexamt1.exceptions.InvalidOperationException;
import com.devops.devopsexamt1.payload.ApiResponse;
import com.devops.devopsexamt1.serviceImpls.MathOperatorImpl;

@RestController
@RequestMapping("/api/v1/do_math")
public class MathController {
    private final MathOperatorImpl mathOperator;

    public MathController(MathOperatorImpl mathOperatorImpl) {
        this.mathOperator = mathOperatorImpl;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody DoMathRequestDto dto) throws InvalidOperationException {
        return ResponseEntity.ok(ApiResponse.success(mathOperator.doMath(dto.getOperand1(), dto.getOperand2(), dto.getOperation())));
    }
}

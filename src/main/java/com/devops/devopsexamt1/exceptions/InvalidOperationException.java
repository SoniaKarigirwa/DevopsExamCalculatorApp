package com.devops.devopsexamt1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOperationException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidOperationException(String message) {
        super(message);
    }

}

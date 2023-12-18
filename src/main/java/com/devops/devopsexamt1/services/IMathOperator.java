package com.devops.devopsexamt1.services;

import com.devops.devopsexamt1.exceptions.InvalidOperationException;

public interface IMathOperator {

    double doMath(double operand1, double operand2, String operation) throws InvalidOperationException;
}

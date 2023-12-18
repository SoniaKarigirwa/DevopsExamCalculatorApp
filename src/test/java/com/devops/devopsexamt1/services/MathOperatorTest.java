package com.devops.devopsexamt1.services;

import com.devops.devopsexamt1.exceptions.InvalidOperationException;
import com.devops.devopsexamt1.serviceImpls.MathOperatorImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathOperatorTest {

    private final MathOperatorImpl mathOperator;

    public MathOperatorTest() {
        this.mathOperator = new MathOperatorImpl();
    }

    @Test
    public void givenTwoOperands_whenAdded_returnsSum() throws InvalidOperationException {
        double actual = mathOperator.doMath(1, 2, "+");
        double expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void givenTwoOperands_whenDivided_returnsQuotient() throws InvalidOperationException {
        double actual = mathOperator.doMath(4, 2, "/");
        double expected = 2;
        assertEquals(expected, actual);
    }
    @Test
    public void givenAnumber_whenDividedByZero_itThrowsException() throws InvalidOperationException {
        assertThrows(InvalidOperationException.class, () ->  mathOperator.doMath(34, 0, "/"));
    }

    @Test
    public void givenTwoOperands_whenSubstracted_returnsDifference() throws InvalidOperationException {
        double actual = mathOperator.doMath(10, 5, "-");
        double expected = 5;
        assertEquals(expected, actual);
    }

    @Test
    public void givenTwoOperands_whenMultiplied_returnsProduct() throws InvalidOperationException {
        double actual = mathOperator.doMath(10, 5, "*");
        double expected = 50;
        assertEquals(expected, actual);
    }

    @Test
    public void givenAnUnknownOperation_whenComputed_throwsRuntimeException() throws InvalidOperationException {
        assertThrows(RuntimeException.class, () -> mathOperator.doMath(10, 5, "&"));
    }


}
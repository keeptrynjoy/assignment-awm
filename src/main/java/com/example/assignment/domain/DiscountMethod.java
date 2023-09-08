package com.example.assignment.domain;

import java.util.function.BiFunction;

public enum DiscountMethod {
    PERCENTAGE((targetAmount, discountVal)->
            (int)((double) targetAmount * (1.0 - discountVal/100.0))),
    FIXED_AMOUNT((targetAmount, discountVal)->
            targetAmount - discountVal);

    private final BiFunction<Integer, Integer, Integer> expression;

    DiscountMethod(BiFunction<Integer, Integer, Integer> expression){
        this.expression = expression;
    }

    public int calculate(int targetAmount, int discountVal){
        return expression.apply(targetAmount,discountVal);
    }
}

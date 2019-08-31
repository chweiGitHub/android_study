package com.emdd.emdd_android.design_model.strategy_model;

public class Division implements ICalculate {
    @Override
    public double calculate(double a, double b) {
        if (b == 0) throw new IllegalArgumentException();
        return a / b;
    }
}

package com.emdd.emdd_android.design_model.strategy_model;


public class Addition implements ICalculate {
    @Override
    public double calculate(double a, double b) {
        return a + b;
    }
}

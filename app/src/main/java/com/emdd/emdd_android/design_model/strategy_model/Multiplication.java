package com.emdd.emdd_android.design_model.strategy_model;

public class Multiplication implements ICalculate {
    @Override
    public double calculate(double a, double b) {
        return a * b;
    }
}

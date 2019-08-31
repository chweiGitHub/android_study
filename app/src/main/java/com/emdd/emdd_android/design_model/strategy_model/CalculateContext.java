package com.emdd.emdd_android.design_model.strategy_model;

public class CalculateContext {


    ICalculate calculate;

    public CalculateContext(ICalculate calculate) {
        this.calculate = calculate;
    }

    public double calculate(double a, double b) {
        return calculate.calculate(a, b);
    }
}

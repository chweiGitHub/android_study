package com.emdd.emdd_android.design_model.strategy_model;


// 从 Java 8 开始，接口也可以拥有默认的方法实现，这是因为不支持默认方法的接口的维护成本太高了。
//接口的成员（字段 + 方法）默认都是 public 的，并且不允许定义为 private 或者 protected。
//接口的字段默认都是 static 和 final 的。


public interface ICalculate {
    /**
     * 具体的计算实现
     */
    double calculate(double a, double b);
}

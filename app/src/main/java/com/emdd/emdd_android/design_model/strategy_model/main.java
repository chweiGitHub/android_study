package com.emdd.emdd_android.design_model.strategy_model;

/**
 * Sends a data [event].
 *
 * Listeners receive this event in a later microtask.
 *
 * Note that a synchronous controller (created by passing true to the `sync`
 * parameter of the `StreamController` constructor) delivers events
 * immediately. Since this behavior violates the contract mentioned here,
 * synchronous controllers should only be used as described in the
 * documentation to ensure that the delivered events always *appear* as if
 * they were delivered in a separate microtask.
 */



public class main {




    public static void main (String[] args){
        CalculateContext calculateContext = new CalculateContext(new Multiplication());
        System.out.println("计算的结果：" + calculateContext.calculate(3, 33));
    }
}



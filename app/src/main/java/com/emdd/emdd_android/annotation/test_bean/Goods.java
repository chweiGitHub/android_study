package com.emdd.emdd_android.annotation.test_bean;

import java.util.Date;

public class Goods {

    int id;
    String name;

    public void print() {
        System.out.println("我已经自动初始化了。 " + new Date().getTime());
    }
}

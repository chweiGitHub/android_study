package com.emdd.emdd_android.section_demo;

public class Goods {

    String name;

    private Goods (){}
    private Goods (String name){this.name  = name;}

    public void print (){
        System.out.println("这里是goods 的print ."+name);
    }

    private  void testPrivateMethod (){
        System.out.println("这里是goods 的 testPrivateMethod");
    }

    private final static String UUID ="uuid-goods";

}

package com.emdd.emdd_android.section_demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.emdd.aspectjdemo.CheckLogin;
import com.emdd.emdd_android.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AspectjDemoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_demo);
    }

    public void click1(View v) {
        System.out.println("点击按钮1 ");
    }

    public void click2(View v) {
        System.out.println("-点击按钮2 ");

        printInfo();
    }

    @CheckLogin
    public void click3(View v) {
        System.out.println("获取用户的个人信息");
    }


    public void printInfo() {
        System.out.println("我是 printInfo ");


        // ------------
        // 反射
        Goods goods = null;
        try {
            // 通过反射的方式创建对象 （无参）
//            goods = Goods.class.newInstance();
//            goods.print();

            // 有参的
            // getConstructor 从公共的构造方法中找
            // getDeclaredConstructor 从所有的 构造方法中找
            Constructor constructor = Goods.class.getDeclaredConstructor(String.class);
            constructor.setAccessible(true); // 设置访问的权限
            Goods goods1 = (Goods) constructor.newInstance("手机");
            goods1.print();

            // 方法
            Method method = Goods.class.getDeclaredMethod("testPrivateMethod"); // 可以直接在后边跟上对应的参数
            method.setAccessible(true);
            method.invoke(goods1);

            // 属性
            Field field = Goods.class.getDeclaredField("name");
            field.setAccessible(true);
            // 获取
            String name = (String) field.get(goods1);
            System.out.println("获取到的 属性值为： " + name);
            // 设置
            field.set(goods1, "小米手机");
            goods1.print();
            // 获取静态的属性
            Field staticField = Goods.class.getDeclaredField("UUID");
            staticField.setAccessible(true);
            String uuid = (String) staticField.get(null);
            System.out.println("获取到的静态属性值为： " + uuid);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void fun1(boolean isLogin) {
        if (isLogin) login();
        else toMain();
    }

    private void login() {
    }

    private void toMain() {
    }

    public void fun1Click(View v) {
        System.out.println("fun1Click");
        fun1(false);
    }

    public void loginClick(View v) {
        System.out.println("loginClick");
        login();
    }

    public void fun1LoginClick(View v) {
        System.out.println("fun1LoginClick");
        fun1(true);
    }

}

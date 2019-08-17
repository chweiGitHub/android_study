package com.emdd.aspectjdemo;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.emdd.aspectjdemo.utils.TokenUtils;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect

public class CustomAspect {

    @Pointcut("execution(* com.emdd.emdd_android.section_demo.AspectjDemoActivity.click1(..))")
    public void executionDemo() {
    }

    @Before("executionDemo()")
    public void executionDemoProcess(JoinPoint joinPoint) {
        System.out.println("executionDemoProcess");

        System.out.println("joinPoint.getSignature()-------> " + joinPoint.getSignature());
        System.out.println("joinPoint.getThis()-------> " + joinPoint.getThis());
        System.out.println("joinPoint.getTarget()-------> " + joinPoint.getTarget());
        System.out.println("joinPoint.getArgs()-------> " + joinPoint.getArgs());


        Toast.makeText(getContext(joinPoint.getThis()), " 点击了按钮1", Toast.LENGTH_SHORT).show();
    }


    @Pointcut("call(* com.emdd.emdd_android.section_demo.AspectjDemoActivity.click2(..))")
    public void callDemo() {

    }

    @Pointcut("call(* com.emdd.emdd_android.section_demo.AspectjDemoActivity.printInfo(..))")
    public void printDemo() {

    }


    @Before("callDemo() || printDemo()")
    public void callDemoProcess(JoinPoint joinPoint) {
        System.out.println("callDemoProcess---------------->");
        System.out.println("joinPoint.getSignature()-------> " + joinPoint.getSignature());
        System.out.println("joinPoint.getThis()-------> " + joinPoint.getThis());
        System.out.println("joinPoint.getTarget()-------> " + joinPoint.getTarget());
        System.out.println("joinPoint.getArgs()-------> " + joinPoint.getArgs());
        Toast.makeText(getContext(joinPoint.getThis()), " 点击了按钮2", Toast.LENGTH_SHORT).show();
    }

    // execution() 是最常用的切点函数，用来匹配方法
    // execution(<修饰符><返回类型><包.类.方法(参数)><异常>)
    @Pointcut("execution(@com.emdd.aspectjdemo.CheckLogin * *(..))")
    public void checkLogin() {
    }

    @Around("checkLogin ()")
    public void checkLoginProcess(ProceedingJoinPoint joinPoint) throws Throwable {
        if (TokenUtils.isLogin()) {
            joinPoint.proceed();
        } else {
            Toast.makeText(getContext(joinPoint.getThis()), "请登录", Toast.LENGTH_SHORT).show();
        }
        return;
    }

    private Context getContext(Object obj) {
        if (obj instanceof Activity) return (Activity) obj;
        if (obj instanceof Fragment) return ((Fragment) obj).getActivity();
        if (obj instanceof View) return ((View) obj).getContext();
        return null;
    }


    @Pointcut("withincode(* com.emdd.emdd_android.section_demo.AspectjDemoActivity.fun1(..))")
    public void fun1Demo() {
    }

    @Pointcut("call(* com.emdd.emdd_android.section_demo.AspectjDemoActivity.login(..))")
    public void loginDemo() {

    }

    @Before("fun1Demo() && loginDemo()")
    public void fun1LoginProcess(JoinPoint joinPoint) {
        System.out.println("fun1中调login的时候输出。");  System.out.println("joinPoint.getSignature()-------> " + joinPoint.getSignature());
        System.out.println("joinPoint.getThis()-------> " + joinPoint.getThis());
        System.out.println("joinPoint.getTarget()-------> " + joinPoint.getTarget());
        System.out.println("joinPoint.getArgs()-------> " + joinPoint.getArgs());

    }


}

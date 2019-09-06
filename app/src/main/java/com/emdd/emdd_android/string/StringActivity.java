package com.emdd.emdd_android.string;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.Calendar;

public class StringActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("-----");
        test();
        System.out.println("--2222---");
    }

    void test() {
        System.out.println("--11111---");

        String str1 = "hello";
        String str2 = "hello";
        boolean r1 = str1.equals(str2);
        System.out.println(r1 + "-=");
        boolean r2 = str1 == str2;
        System.out.println(r2 + "=-");

        long preTime = Calendar.getInstance().getTime().getTime();
        System.out.println("输入前的时间： " + preTime);
        String res = "12345678934524657879678435235252";
        for (int i = 0; i < 9999999; i++) {
            String subStr = res.substring(2, 4);
            int value = Integer.valueOf(subStr);
        }
        long aftTime = Calendar.getInstance().getTime().getTime();
        System.out.println("输入后的时间： " + aftTime);
        long dis = aftTime - preTime;
        System.out.println("间隔时间： " + dis + "ms, " + (dis * 0.001) + "s");


        preTime = Calendar.getInstance().getTime().getTime();
        System.out.println("22输入前的时间： " + preTime);
        StringBuilder stringBuilder = new StringBuilder("12345678934524657879678435235252");
        for (int i = 0; i < 9999999; i++) {
            String subStr = stringBuilder.substring(2, 4);
            int value = Integer.valueOf(subStr);
        }
        aftTime = Calendar.getInstance().getTime().getTime();
        System.out.println("22输入后的时间： " + aftTime);
        dis = aftTime - preTime;
        System.out.println("间隔时间： " + dis + "ms, " + (dis * 0.001) + "s");


        System.out.println("1 << 1:"+(1 << 1));
    }


}

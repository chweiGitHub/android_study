package com.emdd.emdd_android.service;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.emdd.emdd_android.R;

public class ServiceDemoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_demo);
    }

    public void bind(View v) {
        printMsg("bind");


    }

    public void ubind(View v) {
        printMsg("ubind");
    }

    public void getMsg(View v) {
        printMsg("getMsg");
    }

    void printMsg(String msg) {
        System.out.println("---------------> " + msg);
    }
}

package com.emdd.emdd_android;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.emdd.annotation_lib.demo.PrintLog;
import com.emdd.emdd_android.annotation.AutoWired;
import com.emdd.emdd_android.annotation.AutoWiredProcess;
import com.emdd.emdd_android.annotation.test_bean.Goods;

public class AnnotationDemoActivity extends AppCompatActivity {

    @AutoWired
    public Goods goods;
    @AutoWired
    public Goods g1;
    @AutoWired
    public  Goods g2;
    @AutoWired
    public Goods g3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotation);

        AutoWiredProcess.bind(this);

        goods.print();
        g1.print();
        g2.print();
        g3.print();
    }

    private String testAnnotation;
}

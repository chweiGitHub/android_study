package com.emdd.emdd_android.reflect;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.emdd.annotation_lib.demo.PrintLog;
import com.emdd.emdd_android.R;

public class ReflectDemoActivity extends Activity {

    @PrintLog(value=R.id.btn_1, info="info")
    Button btn1;
    Button btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

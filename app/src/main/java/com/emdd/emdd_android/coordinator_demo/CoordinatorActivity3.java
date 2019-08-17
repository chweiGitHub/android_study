package com.emdd.emdd_android.coordinator_demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.emdd.emdd_android.R;
import com.emdd.emdd_android.databinding.ActivityCoordinatorDemoV2Binding;
import com.emdd.emdd_android.databinding.ActivityCoordinatorDemoV3Binding;

public class CoordinatorActivity3 extends AppCompatActivity
        implements View.OnClickListener {


    ActivityCoordinatorDemoV3Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coordinator_demo_v3);
    }

    @Override
    public void onClick(View v) {

    }

}

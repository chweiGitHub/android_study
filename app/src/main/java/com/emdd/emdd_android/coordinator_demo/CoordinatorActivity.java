package com.emdd.emdd_android.coordinator_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.emdd.emdd_android.R;
import com.emdd.emdd_android.databinding.ActivityCoordinatorDemoBinding;
import com.emdd.emdd_android.mvvm.adapter.PhoneAdapter;
import com.emdd.emdd_android.mvvm.bean.PhoneInfo;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorActivity extends AppCompatActivity
        implements View.OnClickListener {

    ActivityCoordinatorDemoBinding binding;

    List<PhoneInfo> phoneInfos = new ArrayList<>();
    DemoAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coordinator_demo);
        binding.setFloatClick(this);

        for (int i = 0; i < 30; i++) {
            phoneInfos.add(new PhoneInfo(i + 1000, String.format("第%d项", (i + 1000))));
        }
        binding.setMyLayoutManager(new LinearLayoutManager(this));
        adapter = new DemoAdapter(phoneInfos);
        binding.setMyAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        showSnackBar("你点我了。。。。 ");
    }

    void showSnackBar(String info) {
        System.out.println("ni dian wo le .........");
//        Snackbar snackbar = Snackbar.make(binding.snackContainer, info, Snackbar.LENGTH_SHORT);
//        snackbar.getView().setBackgroundColor(0xffff0909);
//        ((TextView) snackbar.getView().findViewById(R.id.snackbar_text)).setTextColor(0xff00ff00); //获取Snackbar的message控件，修改字体颜色
//        snackbar.show();
    }

}

package com.emdd.emdd_android.coordinator_demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emdd.emdd_android.R;
import com.emdd.emdd_android.databinding.ActivityCoordinatorDemoBinding;
import com.emdd.emdd_android.mvvm.adapter.PhoneAdapter;
import com.emdd.emdd_android.mvvm.bean.PhoneInfo;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorActivity extends AppCompatActivity
        implements View.OnClickListener {

    RecyclerView recyclerView;
    RecyclerAdapter myAdapter;
    List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_demo);
        recyclerView  = findViewById(R.id.recycler_view);

        list  = new ArrayList<>();
        for (int i =0; i < 30; i ++){
            list.add("item " +i);
        }
        myAdapter  = new RecyclerAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);

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

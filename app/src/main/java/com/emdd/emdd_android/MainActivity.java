package com.emdd.emdd_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.emdd.emdd_android.coordinator_demo.CoordinatorActivity;
import com.emdd.emdd_android.coordinator_demo.CoordinatorActivity2;
import com.emdd.emdd_android.coordinator_demo.CoordinatorActivity3;
import com.emdd.emdd_android.coordinator_demo.CoordinatorActivity4;
import com.emdd.emdd_android.databinding.ActivityMainBinding;
import com.emdd.emdd_android.mvvm.DataBindDemoActivity;
import com.emdd.emdd_android.section_demo.AspectjDemoActivity;
import com.emdd.emdd_android.service.ServiceDemoActivity;


public class MainActivity extends AppCompatActivity {

   public void toAnnotationDemoPage(View v){
       startActivity(new Intent(MainActivity.this, AnnotationDemoActivity.class));
   }
    public void toFilePage(View v){
        startActivity(new Intent(MainActivity.this, FileActivity.class));
    }
    public void toMvvmPage(View v){
        startActivity(new Intent(MainActivity.this, DataBindDemoActivity.class));
    }
    public void toCoordinatorLayoutPage(View v){
        startActivity(new Intent(MainActivity.this, CoordinatorActivity.class));
    }
    public void toAspectDemoPage(View v){
        startActivity(new Intent(MainActivity.this, AspectjDemoActivity.class));
    }
    public void toServicePage (View v){
        startActivity(new Intent(MainActivity.this, ServiceDemoActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  ActivityMainBinding binding  = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
}

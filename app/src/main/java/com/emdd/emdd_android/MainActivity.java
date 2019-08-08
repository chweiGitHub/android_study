package com.emdd.emdd_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.emdd.emdd_android.databinding.ActivityMainBinding;
import com.emdd.emdd_android.mvvm.DataBindDemoActivity;


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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding  = DataBindingUtil.setContentView(this, R.layout.activity_main);

    }
}

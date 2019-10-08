package com.emdd.emdd_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.emdd.emdd_android.coordinator_demo.CoordinatorActivity;
import com.emdd.emdd_android.coordinator_demo.CoordinatorActivity2;
import com.emdd.emdd_android.friends.FriendsDemoActivity;
import com.emdd.emdd_android.game.GameActivity;
import com.emdd.emdd_android.mvvm.DataBindDemoActivity;
import com.emdd.emdd_android.section_demo.AspectjDemoActivity;
import com.emdd.emdd_android.service.ServiceDemoActivity;
import com.emdd.emdd_android.string.StringActivity;
import com.emdd.emdd_android.tablayoutviewpager.TestActivity;
import com.emdd.emdd_android.toolbar.ToolBarActivity;


public class MainActivity extends AppCompatActivity {

    public void toAnnotationDemoPage(View v) {
        startActivity(new Intent(MainActivity.this, AnnotationDemoActivity.class));
    }

    public void toFilePage(View v) {
        startActivity(new Intent(MainActivity.this, FileActivity.class));
    }

    public void toMvvmPage(View v) {
        startActivity(new Intent(MainActivity.this, DataBindDemoActivity.class));
    }

    public void toCoordinatorLayoutPage(View v) {
        startActivity(new Intent(MainActivity.this, CoordinatorActivity.class));
    }

    public void toCoordinatorLayoutPage2(View v) {
        startActivity(new Intent(MainActivity.this, CoordinatorActivity2.class));
    }

    public void toAspectDemoPage(View v) {
        startActivity(new Intent(MainActivity.this, AspectjDemoActivity.class));
    }

    public void toServicePage(View v) {
        startActivity(new Intent(MainActivity.this, ServiceDemoActivity.class));
    }

    public void toStringPage(View v) {
        System.out.println("跳转toStringPage");
        startActivity(new Intent(MainActivity.this, StringActivity.class));
    }

    public void toGamePage(View v) {
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }

    public void toViewPagerPage(View v) {
        startActivity(new Intent(MainActivity.this, TestActivity.class));
    }
    public void toToolBarPage(View v) {
        startActivity(new Intent(MainActivity.this, ToolBarActivity.class));
    }
    public void toFriendsCirclePage(View v) {
        startActivity(new Intent(MainActivity.this, FriendsDemoActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println(" getTheme().toString(): "+ getTheme().toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  ActivityMainBinding binding  = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }
}

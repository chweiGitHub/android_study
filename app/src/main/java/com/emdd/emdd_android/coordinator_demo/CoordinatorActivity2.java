package com.emdd.emdd_android.coordinator_demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.emdd.emdd_android.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.ArrayList;
import java.util.List;

public class CoordinatorActivity2 extends AppCompatActivity
        implements View.OnClickListener, AppBarLayout.OnOffsetChangedListener {

    RecyclerView recyclerView;
    RecyclerAdapter myAdapter;
    List<String> list;

    BottomSheetBehavior behavior;
    AppBarLayout appBarLayout;

    SwipeRefreshLayout swipeRefreshLayout;

    View headImageView;
    boolean isHeadImageViewShow = true;
    int maxSize;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_demo_4);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayout llBottomDlgContainer = findViewById(R.id.ll_bottom_dlg_container);
        behavior = BottomSheetBehavior.from(llBottomDlgContainer);
        headImageView = findViewById(R.id.iv_head_icon);
        headImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                 STATE_COLLAPSED: 默认的折叠状态
//                 STATE_HIDDEN: 通过设置app:behavior_hideable="true"来开启这个状态，用户下滑隐藏
//                 STATE_EXPANDED: 完全展开的状态
//                 STATE_DRAGGING: 拖拽的过程
                if (behavior.getState() == BottomSheetBehavior.STATE_HIDDEN) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    System.out.println("显示");
                } else {
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    System.out.println("隐藏");
                }
            }
        });
        appBarLayout = findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(this);
        maxSize = appBarLayout.getTotalScrollRange();

        swipeRefreshLayout = findViewById(R.id.refresh_view);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(0x01, 2000);
            }
        });

        list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("item " + i);
        }
        myAdapter = new RecyclerAdapter(list);
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

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0x01) {
                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        }
    };

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        System.out.println("i==============> " + i);
        if (i == 0) {
            swipeRefreshLayout.setEnabled(true);
        } else {
            swipeRefreshLayout.setEnabled(false);
        }
        if (maxSize == 0)
            maxSize = appBarLayout.getTotalScrollRange();
        int percentage = (Math.abs(i)) * 100 / maxSize;
        if (percentage > 10 && isHeadImageViewShow) {
            isHeadImageViewShow = false;
            headImageView.animate().alpha(0).setDuration(600).start();
        }
        if (percentage <= 10 && !isHeadImageViewShow) {
            isHeadImageViewShow = true;
            headImageView.animate().alpha(1).setDuration(600).start();
        }
    }
}

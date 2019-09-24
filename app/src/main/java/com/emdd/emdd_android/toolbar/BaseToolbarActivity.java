package com.emdd.emdd_android.toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.emdd.emdd_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseToolbarActivity extends AppCompatActivity {


    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.main_container)
    FrameLayout mainContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);
        ButterKnife.bind(this);

        initToolbar();
        if (getLayoutId() >0){
            View view =LayoutInflater.from(this).inflate(getLayoutId(), null);
            mainContainer.addView(view);
        }

    }

    private void initToolbar (){
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //添加溢出菜单
        toolBar.inflateMenu(R.menu.menu_setting);
        // 添加菜单点击事件
        toolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(BaseToolbarActivity.this, "" + item.getItemId(), Toast.LENGTH_SHORT).show();
                switch (item.getItemId()) {
                    case R.id.item_setting:
                        //点击设置菜单
                        break;
                }
                return false;
            }
        });
    }
    public void hideToolbar (){
        if(toolBar!=null && toolBar.getContext()!= null) {
            toolBar.setVisibility(View.GONE);
        }
    }

    public void showToolbar (){
        if(toolBar!=null && toolBar.getContext()!= null) {
            toolBar.setVisibility(View.VISIBLE);
        }
    }
    public void setTitle (String title){
        if(toolBar!=null && toolBar.getContext()!= null) {
            toolBar.setTitle(title);
        }
    }
    private boolean uiAvailable =false;
    private int getLayoutId (){return 0;}
}

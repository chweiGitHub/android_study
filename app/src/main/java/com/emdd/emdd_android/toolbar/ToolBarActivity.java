package com.emdd.emdd_android.toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.emdd.emdd_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ToolBarActivity extends BaseToolbarActivity {


    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tool_bar);
        ButterKnife.bind(this);

    }
}

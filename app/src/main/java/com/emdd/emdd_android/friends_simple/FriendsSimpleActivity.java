package com.emdd.emdd_android.friends_simple;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.emdd.emdd_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendsSimpleActivity extends AppCompatActivity {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_friend_simple);
        ButterKnife.bind(this);


    }
}

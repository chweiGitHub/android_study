package com.emdd.emdd_android.search;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.emdd.emdd_android.R;

public class SearchActivity1  extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search);

        findViewById(R.id.search_total_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShareAnimation (view);
            }
        });


    }
    private void showShareAnimation(View view) {
        Intent intent = new Intent(SearchActivity1.this, SearchActivity.class);

        //创建一个rect 对象来存储共享元素的位置信息
        Rect rect = new Rect();
        //获取元素的位置信息
        view.getGlobalVisibleRect(rect);
        //将位置信息附加到intent 上
        intent.setSourceBounds(rect);
        CustomImage customImage = (CustomImage) view;
        intent.putExtra("image", customImage.getImageId());
        startActivity(intent);
        //用于屏蔽 activity 默认的转场动画效果
        overridePendingTransition(0, 0);
    }
}

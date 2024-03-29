package com.emdd.friendscircle.others;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.emdd.friendscircle.utils.Utils;


/**
 * @author KCrason
 * @date 2018/5/2
 */
public class FriendsCircleAdapterDivideLine extends RecyclerView.ItemDecoration {
    private int mDivideHeight;

    public FriendsCircleAdapterDivideLine() {
        mDivideHeight = Utils.dp2px(0.5f);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, mDivideHeight);
    }
}

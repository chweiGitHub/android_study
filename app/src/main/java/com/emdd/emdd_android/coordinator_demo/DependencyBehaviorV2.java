package com.emdd.emdd_android.coordinator_demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

public class DependencyBehaviorV2 extends CoordinatorLayout.Behavior<View> {

    public DependencyBehaviorV2() {
        super();
    }

    public DependencyBehaviorV2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /// 开始滑动的时候， 要返回true才有效
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
//        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
        return child instanceof ImageView && ViewCompat.SCROLL_AXIS_VERTICAL == axes;
    }

    /// 准备滑动的时候调用，有要滑动的偏移量 x & y。
    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        ViewCompat.offsetTopAndBottom(child, dy);
    }
}
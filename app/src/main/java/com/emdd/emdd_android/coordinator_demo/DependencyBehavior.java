package com.emdd.emdd_android.coordinator_demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class DependencyBehavior extends CoordinatorLayout.Behavior<View> {

    public DependencyBehavior() {
        super();
    }

    public DependencyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //判断依赖是否为DragView
        return dependency instanceof DragView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //获取DragView的顶部，让child位于DragView的左上方
        int top = dependency.getTop();
        int childHeight = child.getHeight();
        child.setY(top - childHeight);
        child.setX(dependency.getLeft());
        return true;
    }
}
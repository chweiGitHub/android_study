package com.emdd.emdd_android.coordinator_demo;

import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

public class CustomBehavior extends AppBarLayout.Behavior {


    @Override
    public boolean onStartNestedScroll(CoordinatorLayout parent, AppBarLayout child, View directTargetChild, View target, int nestedScrollAxes, int type) {

//        (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0

        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type);
    }
}

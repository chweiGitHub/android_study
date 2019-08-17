package com.emdd.emdd_android.coordinator_demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.emdd.emdd_android.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final
    FloatingActionButton child, final View directTargetChild, final View target, final int
                                               nestedScrollAxes) {
        // 确保是竖直判断的滚动
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll
                (coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final
    FloatingActionButton child, final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
            child.hide();
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            child.show();
        }
    }


//    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    private static final android.view.animation.Interpolator INTERPOLATOR =
//            new FastOutSlowInInterpolator();
//    private boolean mIsAnimatingOut = false;
//
//    // Same animation that FloatingActionButton.Behavior uses to
//    // hide the FAB when the AppBarLayout exits
//    private void animateOut(final FloatingActionButton button) {
//        if (Build.VERSION.SDK_INT >= 14) {
//            ViewCompat.animate(button).scaleX(0.0F).scaleY(0.0F).alpha(0.0F)
//                    .setInterpolator(INTERPOLATOR).withLayer()
//                    .setListener(new ViewPropertyAnimatorListener() {
//                        public void onAnimationStart(View view) {
//                            ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
//                        }
//
//                        public void onAnimationCancel(View view) {
//                            ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
//                        }
//
//                        public void onAnimationEnd(View view) {
//                            ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
//                            view.setVisibility(View.GONE);
//                        }
//                    }).start();
//        } else {
//            Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.fab_out);
//            anim.setInterpolator(INTERPOLATOR);
//            anim.setDuration(200L);
//            anim.setAnimationListener(new Animation.AnimationListener() {
//                public void onAnimationStart(Animation animation) {
//                    ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
//                }
//
//                @SuppressLint("RestrictedApi")
//                public void onAnimationEnd(Animation animation) {
//                    ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
//                    button.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onAnimationRepeat(final Animation animation) {
//                }
//            });
//            button.startAnimation(anim);
//        }
//    }
//
//    // Same animation that FloatingActionButton.Behavior
//    // uses to show the FAB when the AppBarLayout enters
//    @SuppressLint("RestrictedApi")
//    private void animateIn(FloatingActionButton button) {
//        button.setVisibility(View.VISIBLE);
//        if (Build.VERSION.SDK_INT >= 14) {
//            ViewCompat.animate(button).scaleX(1.0F).scaleY(1.0F).alpha(1.0F)
//                    .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
//                    .start();
//        } else {
//            Animation anim = AnimationUtils.loadAnimation(button.getContext(), R.anim.fab_in);
//            anim.setDuration(200L);
//            anim.setInterpolator(INTERPOLATOR);
//            button.startAnimation(anim);
//        }
//    }
}
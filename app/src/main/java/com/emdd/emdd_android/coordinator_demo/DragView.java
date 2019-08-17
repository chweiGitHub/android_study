package com.emdd.emdd_android.coordinator_demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.ViewCompat;

public class DragView extends AppCompatTextView {



    private final int mSlop;
    private float mLastX;
    private float mLastY;

    public DragView(Context context) {
        this(context,null);
    }

    public DragView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setClickable(true);
        mSlop = ViewConfiguration.getTouchSlop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getRawX();
                mLastY = event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                int deltax = (int) (event.getRawX() - mLastX);
                int deltay = (int) (event.getRawY() - mLastY);
                if (Math.abs(deltax) > mSlop || Math.abs(deltay) > mSlop) {
                    ViewCompat.offsetTopAndBottom(this,deltay);
                    ViewCompat.offsetLeftAndRight(this,deltax);
                    mLastX = event.getX(); // .getRawX();
                    mLastY = event.getRawY();
                }
                break;
            case MotionEvent.ACTION_UP:
                mLastX = event.getRawX();
                mLastY = event.getRawY();
                break;
            default:
                break;
        }
        return true;
    }
}

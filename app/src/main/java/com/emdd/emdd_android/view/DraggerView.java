//package com.emdd.emdd_android.view;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.PointF;
//import android.util.AttributeSet;
//import android.util.TypedValue;
//import android.view.MotionEvent;
//import android.view.View;
//
//import androidx.annotation.Nullable;
//
///**
// * description: 消息气泡拖拽的 View
// * author: Darren on 2017/7/21 10:40
// * email: 240336124@qq.com
// * version: 1.0
// */
//public class DraggerView extends View {
//    // 拖拽圆的圆心点
//    private PointF mDragPoint;
//    // 固定圆的圆心点
//    private PointF mFixationPoint;
//    // 拖拽圆的半径
//    private int mDragRadius = 10;
//    // 固定圆的半径
//    private int mFixationRadius = 7;
//    // 固定圆的最小半径
//    private int FIXATION_RADIUS_MIN = 3;
//    // 固定圆的最大半径
//    private int FIXATION_RADIUS_MAX = 7;
//    // 用来绘制的画笔
//    private Paint mPaint;
//
//    public DraggerView(Context context) {
//        this(context, null);
//    }
//
//    public DraggerView(Context context, @Nullable AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public DraggerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        // 初始化画笔
//        mPaint = new Paint();
//        mPaint.setColor(Color.RED);
//        mPaint.setDither(true);
//        mPaint.setAntiAlias(true);
//        // 初始化一些距离
//        mDragRadius = dip2px(mDragRadius);
//        mFixationRadius = dip2px(mFixationRadius);
//        FIXATION_RADIUS_MIN = dip2px(FIXATION_RADIUS_MIN);
//        FIXATION_RADIUS_MAX = dip2px(FIXATION_RADIUS_MAX);
//    }
//
//    private int dip2px(int dip) {
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dip,getResources().getDisplayMetrics());
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        if (mDragPoint == null && mFixationPoint == null) {
//            return;
//        }
//
//        // 1.绘制拖拽圆
//        canvas.drawCircle(mDragPoint.x, mDragPoint.y, mDragRadius, mPaint);
//
//        // 计算两个圆之间的距离
//        int distance = BubbleUtils.getDistance(mDragPoint, mFixationPoint);
//
//        // 计算固定圆的半径，距离越大圆半径越小
//        mFixationRadius = FIXATION_RADIUS_MAX - distance / 14;
//
//        if (mFixationRadius > FIXATION_RADIUS_MIN) {
//            // 2.绘制固定圆
//            canvas.drawCircle(mFixationPoint.x, mFixationPoint.y, mFixationRadius, mPaint);
//        }
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                initPoint(event.getX(), event.getY());
//                break;
//            case MotionEvent.ACTION_MOVE:
//                updateDragPoint(event.getX(), event.getY());
//                break;
//            case MotionEvent.ACTION_UP:
//
//                break;
//        }
//        invalidate();
//        return true;
//    }
//
//    /**
//     * 更新拖拽圆的位置
//     *
//     * @param x
//     * @param y
//     */
//    private void updateDragPoint(float x, float y) {
//        mDragPoint.x = x;
//        mDragPoint.y = y;
//    }
//
//    /**
//     * 初始化圆的位置
//     *
//     * @param x
//     * @param y
//     */
//    private void initPoint(float x, float y) {
//        mDragPoint = new PointF(x, y);
//        mFixationPoint = new PointF(x, y);
//    }
//}
package com.emdd.emdd_android.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.provider.CalendarContract;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.emdd.emdd_android.R;


/**
 * Created by user on 2017/5/11.
 * 电池
 */

public class BattaryView extends View {

    private final Paint mPaint;
    private int battaryPercent;//电量百分比
    private int backgroundColor; // 背景色
    private int foregroundColor;//前景色
    private float round;//圆角大小，这里写死，省去使用时计算
    private int embossWidth; // 凸点的宽
    private int embossHeight;   // 凸点的高

    public void setBattaryPercent(int battaryPercent) {
        this.battaryPercent = battaryPercent;
        postInvalidate();
    }

    public BattaryView(Context context) {
        this(context, null);
    }

    public BattaryView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BattaryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BattaryView, 0, defStyleAttr);
        for (int i = 0; i < typedArray.length(); i++) {
            int attr = typedArray.getIndex(i);
            switch (attr) {
                case R.styleable.BattaryView_battaryPercnet:
                    battaryPercent = typedArray.getInteger(attr, 20);
                    break;
                case R.styleable.BattaryView_backgroundColor:
                    backgroundColor = typedArray.getColor(attr, Color.argb(77, 10, 10, 255));
                    break;
                case R.styleable.BattaryView_foregroundColor:
                    foregroundColor = typedArray.getColor(attr, Color.BLUE);
                    break;
                case R.styleable.BattaryView_embossWidth:
                    int width = typedArray.getInteger(attr, 0);
                    embossWidth = dip2px(getContext(),  width);
                    break;
                case R.styleable.BattaryView_embossHeight:
                    int heigth = typedArray.getInteger(attr, 0);
                    embossHeight  = dip2px(getContext(), heigth);
                    break;
            }
        }
        mPaint = new Paint();
        mPaint.setAntiAlias(true);


    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        int width = 0, height = 0;
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                width = specSize + getPaddingRight() + getPaddingLeft();
                break;
            case MeasureSpec.AT_MOST:
                break;
        }
        specMode = MeasureSpec.getMode(heightMeasureSpec);
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode) {
            case MeasureSpec.EXACTLY:
                height = specSize + getPaddingTop() + getPaddingBottom();
                break;
            case MeasureSpec.AT_MOST:
                break;
        }
        setMeasuredDimension(width, height);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int borderWidth = dip2px(getContext(), 12);
        round = 40; // getHeight() / 20f;//把圆角写死，省去使用时计算
        mPaint.setStyle(Paint.Style.FILL);//填充模式
        //mPaint.setColor(backgroundColor);
        //电池区域的高，宽
        int imgH = getHeight() - getPaddingTop() - getPaddingBottom();
        int imgW = getWidth() - getPaddingLeft() - getPaddingRight();// (getWidth() < getHeight() / 2) ? (getWidth() - getPaddingLeft() - getPaddingRight()) : getHeight() / 2;
        //根据电量百分比得到的矩形,top和bottom先占位，等一下再重新赋值，也可以new RectF()里面空参，四个属性挨个设置
        RectF rectF = new RectF(
                (float) getPaddingLeft() + borderWidth,
                0,
                (float) getPaddingLeft() - borderWidth + imgW,
                0);
        float spaceTop = (getHeight() - imgH) / 2 + getPaddingTop();
        rectF.top = spaceTop + (1.0f - battaryPercent * 1.0f / 100) * imgH;
        rectF.bottom = getHeight() - spaceTop;
        //上面这三行其实有电多余，由于刚开始是想设置at most时的状况的，后来发现太麻烦，急着用就先这样了。

        Log.i("=========1====>", "spacetop:" + spaceTop + "---" + getHeight() + "---" + imgH + "---");

        int layerId = canvas.saveLayer(0, 0, canvas.getWidth(), canvas.getHeight(), null, Canvas.ALL_SAVE_FLAG);
        int h1 = embossHeight == 0 ? imgH / 8 : embossHeight;//电池凸点的高度
        int w1 = embossWidth == 0 ? imgW / 3 : embossWidth;//电池凸点的宽度
        //绘制背景
        mPaint.setColor(backgroundColor);
        canvas.drawRoundRect(rectF.left, h1 + getPaddingTop(), rectF.right, getHeight() - getPaddingBottom(), round, round, mPaint);
        //canvas.drawRect(getPaddingLeft() + w1,getPaddingTop(),rectF.right - w1,h1 + getPaddingTop(),mPaint);

        canvas.drawRoundRect(getPaddingLeft() + borderWidth + w1, getPaddingTop() + h1 / 2.0f, rectF.right - w1 - borderWidth, (float) (getPaddingTop() + h1 * 3.0f / 2.0f), round, round, mPaint);

        if (battaryPercent <= 90) {
            RectF rectF2 = new RectF(
                    (float) getPaddingLeft() + borderWidth,
                    getPaddingTop() + h1,
                    (float) getPaddingLeft() + imgW - borderWidth,
                    rectF.bottom);
            canvas.clipRect(rectF2);

            mPaint.setColor(Color.parseColor("#ffffff"));
            canvas.drawRoundRect(rectF.left , h1 + getPaddingTop(), rectF.right , getHeight() - getPaddingBottom(), round, round, mPaint);
            mPaint.setColor(backgroundColor);
            canvas.drawRoundRect(rectF.left , h1 + getPaddingTop(), rectF.right , getHeight() - getPaddingBottom(), round, round, mPaint);

            Log.i("========2=====>", "reactF:" + rectF.toString() + "---" + getPaddingTop() + "---" + h1 + "---");

//        canvas.clipRect(rectF2);
        }
        //绘制有电部分
        canvas.save();

        canvas.clipRect(rectF);
        mPaint.setColor(foregroundColor);
        Log.i("=========3====>", "reactF:" + rectF.toString() + "---" + getPaddingTop() + "---" + h1 + "---");
        canvas.drawRoundRect(rectF.left , getPaddingTop() + h1, rectF.right , rectF.bottom, round, round, mPaint);
//        canvas.drawRect(rectF.left + w1, rectF.top, rectF.right - w1, getPaddingTop() + h1 + h1 / 10, mPaint);
        canvas.drawRoundRect(getPaddingLeft() + w1 , getPaddingTop() + h1 / 2.0f, rectF.right - w1 - borderWidth, (float) (getPaddingTop() + h1 * 3.0f / 2.0f), round, round, mPaint);


        canvas.restore();
        canvas.restoreToCount(layerId);
    }

    public void setColor(int foregroundColor,
                         int backgroundColor) {
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        invalidate();
    }

}

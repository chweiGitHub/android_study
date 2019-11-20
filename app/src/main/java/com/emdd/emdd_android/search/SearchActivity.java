package com.emdd.emdd_android.search;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.emdd.emdd_android.R;

public class SearchActivity extends AppCompatActivity {
    private static final int MESSAGE_SHOW_KEYBOARD = 1;
    private static final int MESSAGE_SHOW_EDIT = 2;
    public static final int DURATION = 300;

    private static final AccelerateDecelerateInterpolator DEFAULT_INTERPOLATOR = new AccelerateDecelerateInterpolator();
    private static final String SCALE_WIDTH = "SCALE_WIDTH";
    private static final String SCALE_HEIGHT = "SCALE_HEIGHT";
    private static final String TRANSITION_X = "TRANSITION_X";
    private static final String TRANSITION_Y = "TRANSITION_Y";

    private Activity instance = SearchActivity.this;

    /**
     * 存储图片缩放比例和位移距离
     */
    private Bundle mScaleBundle = new Bundle();
    private Bundle mTransitionBundle = new Bundle();

    /**
     * 屏幕宽度和高度
     */
    private int mScreenWidth;
    private int mScreenHeight;

    /**
     * 上一个界面图片的宽度和高度
     */
    private int mOriginWidth;
    private int mOriginHeight;

    /**
     * 上一个界面图片的位置信息
     */
    private Rect mRect;

    private CustomImage mImageView;
    private EditText searchEdit;
    private RelativeLayout searchTop;
    private TextView searchLine;

    @Override
    public void onBackPressed() {
        // 使用退场动画
        runExitAnim();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_search_2);

        // 获得屏幕尺寸
        getScreenSize();

        // 初始化界面
        mImageView = (CustomImage) findViewById(R.id.activity_search_img);
        searchEdit = (EditText)findViewById(R.id.search_content);
        searchTop = (RelativeLayout)findViewById(R.id.activity_search_top);
        searchLine = (TextView)findViewById(R.id.activity_search_line);

        // 初始化场景
        initial();

        // 设置入场动画
        runEnterAnim();

        //动态显示搜索结果
        showSearchResult();

    }


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MESSAGE_SHOW_KEYBOARD:
                    //CommonUtil.showKeyboard(instance, searchEdit);
                    break;
                case MESSAGE_SHOW_EDIT:
                    mImageView.setVisibility(View.GONE);
                    searchTop.setVisibility(View.VISIBLE);
                    searchLine.setVisibility(View.VISIBLE);
                    searchEdit.requestFocus();
                    break;
            }
        }
    };


    /**
     * 初始化场景
     */
    private void initial() {
        // 获取上一个界面传入的信息
        mRect = getIntent().getSourceBounds();
        //图片资源 ID
        int mRescourceId = getIntent().getExtras().getInt("image");

        // 获取上一个界面中，图片的宽度和高度
        mOriginWidth = mRect.right - mRect.left;
        mOriginHeight = mRect.bottom - mRect.top;

        // 设置 ImageView 的位置，使其和上一个界面中图片的位置重合
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mOriginWidth, mOriginHeight);
        params.setMargins(mRect.left, mRect.top - getStatusBarHeight(), mRect.right, mRect.bottom);
        mImageView.setLayoutParams(params);

        // 设置 ImageView 的图片和缩放类型
        mImageView.setImageResource(mRescourceId);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        // 根据上一个界面传入的图片资源 ID，获取图片的 Bitmap 对象。
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(mRescourceId);
        Bitmap bitmap = bitmapDrawable.getBitmap();

        // 计算图片缩放比例和位移距离
        getBundleInfo(bitmap);

    }

    /**
     * 计算图片缩放比例，以及位移距离
     */
    private void getBundleInfo(Bitmap bitmap) {
        // 计算图片缩放比例，并存储在 bundle 中
        if (bitmap.getWidth() >= bitmap.getHeight()) {
            mScaleBundle.putFloat(SCALE_WIDTH, (float) mScreenWidth / mOriginWidth);
            mScaleBundle.putFloat(SCALE_HEIGHT, (float) bitmap.getHeight() / mOriginHeight);
        } else {
            mScaleBundle.putFloat(SCALE_WIDTH, (float) bitmap.getWidth() / mOriginWidth);
            mScaleBundle.putFloat(SCALE_HEIGHT, (float) mScreenHeight / mOriginHeight);
        }
        // 计算位移距离，并将数据存储到 bundle 中
        mTransitionBundle.putFloat(TRANSITION_X, mScreenWidth / 2 - (mRect.left + (mRect.right - mRect.left) / 2));

//        mTransitionBundle.putFloat(TRANSITION_Y, mScreenHeight / 2 - (mRect.top + (mRect.bottom - mRect.top) / 2));
        mTransitionBundle.putFloat(TRANSITION_Y, -(mRect.top-getStatusBarHeight()));
    }

    /**
     * 模拟入场动画
     */
    private void runEnterAnim() {
        mImageView.animate()
                .setInterpolator(DEFAULT_INTERPOLATOR)
                .setDuration(DURATION)
                .scaleX(mScaleBundle.getFloat(SCALE_WIDTH))
                .scaleY(mScaleBundle.getFloat(SCALE_HEIGHT))
                .translationX(mTransitionBundle.getFloat(TRANSITION_X))
                .translationY(mTransitionBundle.getFloat(TRANSITION_Y))
                .start();
        mImageView.setVisibility(View.VISIBLE);

        //add
        mHandler.sendEmptyMessageDelayed(MESSAGE_SHOW_EDIT,DURATION);
        mHandler.sendEmptyMessageDelayed(MESSAGE_SHOW_KEYBOARD,DURATION*2);
    }

    /**
     * 模拟退场动画
     */
    @SuppressWarnings("NewApi")
    private void runExitAnim() {
        //add
        searchLine.setVisibility(View.GONE);
        searchTop.setVisibility(View.GONE);
        mImageView.setVisibility(View.VISIBLE);

        mImageView.animate()
                .setInterpolator(DEFAULT_INTERPOLATOR)
                .setDuration(DURATION)
                .scaleX(1)
                .scaleY(1)
                .translationX(0)
                .translationY(0)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                        overridePendingTransition(0, 0);
                    }
                })
                .start();
    }

    /**
     * 获取屏幕尺寸
     */
    private void getScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mScreenWidth = size.x;
        mScreenHeight = size.y;
    }

    /**
     * 获取状态栏高度
     */
    private int getStatusBarHeight() {
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            return getResources().getDimensionPixelSize(resourceId);
        }
        return -1;
    }

    private void showSearchResult(){
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //搜索的匹配算法
                Log.d("SearchActivity"," afterTextChanged 调用了 s="+s.toString());

            }
        });
    }
}

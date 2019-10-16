package com.emdd.emdd_android.friends;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.bumptech.glide.Glide;
import com.emdd.emdd_android.MainActivity;
import com.emdd.emdd_android.R;
import com.emdd.friendscircle.adapters.FriendCircleAdapter;
import com.emdd.friendscircle.beans.FriendCircleBean;
import com.emdd.friendscircle.interfaces.OnPraiseOrCommentClickListener;
import com.emdd.friendscircle.others.DataCenter;
import com.emdd.friendscircle.others.FriendsCircleAdapterDivideLine;
import com.emdd.friendscircle.others.GlideSimpleTarget;
import com.emdd.friendscircle.utils.Utils;
import com.emdd.friendscircle.widgets.EmojiPanelView;
import com.vondear.rxtool.RxWebViewTool;

import java.util.ArrayList;
import java.util.List;

import ch.ielse.view.imagewatcher.ImageWatcher;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FriendsDemoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        OnPraiseOrCommentClickListener, ImageWatcher.OnPictureLongPressListener, ImageWatcher.Loader {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Disposable mDisposable;
    private FriendCircleAdapter mFriendCircleAdapter;
    private ImageWatcher mImageWatcher;
    private EmojiPanelView mEmojiPanelView;
    private static final String TAG = "FriendsDemoActivity";
    private RecyclerView recyclerView;
    private View titleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_friends);
        titleView = findViewById(R.id.view_title);
        mEmojiPanelView = findViewById(R.id.emoji_panel_view);
        mEmojiPanelView.initEmojiPanel(DataCenter.emojiDataSources);
        mSwipeRefreshLayout = findViewById(R.id.swpie_refresh_layout);
        recyclerView = findViewById(R.id.recycler_view);
        mSwipeRefreshLayout.setOnRefreshListener(this);

//        findViewById(R.id.img_back).setOnClickListener(v ->
//                startActivity(new Intent(FriendsDemoActivity.this, EmojiPanelActivity.class)));
        Log.e(TAG, "INIT ....");
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Glide.with(FriendsDemoActivity.this).resumeRequests();
                } else {
                    Glide.with(FriendsDemoActivity.this).pauseRequests();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e(TAG, "滑动的距离： " + dy);
                int distance  = getScollYDistance();
                Log.e(TAG, "--------------------------------------------> "+distance);
                updateTitleAlpah(distance);
            }
        });
        mImageWatcher = findViewById(R.id.image_watcher);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new FriendsCircleAdapterDivideLine());
        mFriendCircleAdapter = new FriendCircleAdapter(this, recyclerView, mImageWatcher);
        recyclerView.setAdapter(mFriendCircleAdapter);
        mImageWatcher.setTranslucentStatus(Utils.calcStatusBarHeight(this));
        mImageWatcher.setErrorImageRes(R.mipmap.error_picture);
        mImageWatcher.setOnPictureLongPressListener(this);
        mImageWatcher.setLoader(this);
        mSwipeRefreshLayout.setSlingshotDistance(500);
        mSwipeRefreshLayout.setDistanceToTriggerSync(500);
        Utils.showSwipeRefreshLayout(mSwipeRefreshLayout, this::asyncMakeData);

    }

    private double headerHeight = 200.0;
    private void updateTitleAlpah(double distance) {
        float alpha = 1;
        if (distance < 30)alpha = 0.3f;
        else if (distance >200){
            alpha = 1;
        } else {
            alpha  = (float) (distance / headerHeight);
        }
        titleView.setAlpha(alpha);
    }


    public int getScollYDistance() {
        if (recyclerView.getLayoutManager() != null && recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int position = manager.findFirstVisibleItemPosition();
            View firstVisiableChildView = manager.findViewByPosition(position);
            int itemHeight = firstVisiableChildView.getHeight();
            return (position) * itemHeight - firstVisiableChildView.getTop();
        }
        return 0;
    }


    private void asyncMakeData() {
        mDisposable = Single.create((SingleOnSubscribe<List<FriendCircleBean>>) emitter ->
                emitter.onSuccess(DataCenter.makeFriendCircleBeans(this)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((friendCircleBeans, throwable) -> {
                    Utils.hideSwipeRefreshLayout(mSwipeRefreshLayout);
                    if (friendCircleBeans != null && throwable == null) {
                        for (int i = 0; i < friendCircleBeans.size(); i++) {
                            List<String> imgs = friendCircleBeans.get(i).getImageUrls();
                            List imageLIst = new ArrayList();
                            if (imgs != null && imgs.size() > 0) {
                                for (int a = 0; a < imgs.size(); a++) {
                                    imageLIst.add("https://p2.music.126.net/LKX3NWX8bbExnB8K7ZQ9eQ==/109951163784237281.jpg?param=50y50");
                                }
                                friendCircleBeans.get(i).setImageUrls(imageLIst);
                            }
                        }

                        friendCircleBeans.add(null);
                        mFriendCircleAdapter.setFriendCircleBeans(friendCircleBeans);
                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onRefresh() {
        asyncMakeData();
    }

    @Override
    public void onPraiseClick(int position) {
        Toast.makeText(this, "You Click Praise!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCommentClick(int position) {
//        Toast.makeText(this, "you click comment", Toast.LENGTH_SHORT).show();
        mEmojiPanelView.showEmojiPanel();
    }

    @Override
    public void onBackPressed() {
        if (!mImageWatcher.handleBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {

    }


    @Override
    public void load(Context context, String url, ImageWatcher.LoadCallback lc) {
        Glide.with(context).asBitmap().load(url).into(new GlideSimpleTarget(lc));
    }
}

package com.emdd.emdd_android.tablayoutviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.emdd.emdd_android.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static androidx.recyclerview.widget.DividerItemDecoration.VERTICAL;


public class TestFragmentv2 extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.list_view_user)
    RecyclerView listViewUser;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.search_view)
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    private void init() {

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("setOnSearchClickListenersetOnSearchClickListenersetOnSearchClickListenersetOnSearchClickListener");
            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                loadDataList(true);
                smartRefreshLayout.finishRefresh(600);
            }
        }).setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadDataList(false);
                smartRefreshLayout.finishLoadMore(600);
            }
        });
        setUnderLinetransparent(searchView);

        adapter = new UserAdapter();
        adapter.setListener(new CallbackListener() {
            @Override
            public void onItemClickListener(int index) {
                Toast.makeText(getContext(), "index: " + index, Toast.LENGTH_SHORT).show();
            }
        });
        searchView.setQueryHint("请输入key");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("onQueryTextSubmit----> " + query);
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("onQueryTextChange----> " + newText);
                return false;
            }
        });
        adapter.setData(list);
        listViewUser.setLayoutManager(new LinearLayoutManager(getContext()));
        listViewUser.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        listViewUser.setAdapter(adapter);

        new CountDownTimer(3000, 3000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                System.out.println("结束了。。。。");
                loadDataList(true);
            }
        }.start();
    }

    /**
     * 设置SearchView下划线透明
     **/
    private void setUnderLinetransparent(SearchView searchView) {
        try {
            Class<?> argClass = searchView.getClass();
            // mSearchPlate是SearchView父布局的名字
            Field ownField = argClass.getDeclaredField("mSearchPlate");
            ownField.setAccessible(true);
            View mView = (View) ownField.get(searchView);
            mView.setBackgroundColor(Color.TRANSPARENT);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    List<ItemData> list = new ArrayList<>();

    private void loadDataList(boolean isFromStart) {
        System.out.println("isFromStart---> " + isFromStart);
        List<ItemData> list1 = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list1.add(new ItemData(i, "第" + (i + 1) + "项"));
        }
        if (isFromStart) {
            list.clear();
        }
        list.addAll(list1);
        adapter.setData(list);
        adapter.notifyDataSetChanged();
    }

    UserAdapter adapter;

    class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

        List<ItemData> data = new ArrayList<>();

        UserAdapter() {

        }

        UserAdapter(CallbackListener listener) {
            this.listener = listener;
        }

        public void setData(List<ItemData> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_user_view, parent, false);
            return new UserViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            holder.tvValue.setText(data.get(position).value);
            holder.tvValue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClickListener(position);
                    }
                }
            });
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @Override
        public int getItemCount() {
            System.out.println("data.size()---> " + data.size());
            return data.size();
        }


        private CallbackListener listener;

        public void setListener(CallbackListener listener) {
            this.listener = listener;
        }

    }

    public interface CallbackListener {
        public void onItemClickListener(int index);
    }


    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvValue;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvValue = itemView.findViewById(R.id.tv_value);
        }
    }

}

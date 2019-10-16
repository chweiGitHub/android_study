package com.emdd.emdd_android.friends_simple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emdd.emdd_android.R;

import java.util.ArrayList;
import java.util.List;

public class FriendsSimpleAdapter extends RecyclerView.Adapter<FriendsSimpleViewHolder> {

    public FriendsSimpleAdapter(Context context) {
        this.context = context;
    }

    private List<FriendsSimpleItemData> list = new ArrayList<>();
    private Context context;

    public void setList(List<FriendsSimpleItemData> list) {
        if (list == null) return;
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FriendsSimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return viewType == 1001
                ? new FriendsSimpleViewHolder(new TextView(context), viewType)
                : new FriendsSimpleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user_view, parent, false), viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? 1001 : 1002;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsSimpleViewHolder holder, int position) {
        if (position ==0){

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}

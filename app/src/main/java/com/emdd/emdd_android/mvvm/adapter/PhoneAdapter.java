package com.emdd.emdd_android.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.databinding.DataBindingUtil;

import com.emdd.emdd_android.R;
import com.emdd.emdd_android.databinding.ListItemViewBinding;
import com.emdd.emdd_android.mvvm.bean.PhoneInfo;

import java.util.List;

public class PhoneAdapter extends BaseAdapter {
    public PhoneAdapter(List<PhoneInfo> phoneInfoList) {
        this.phoneInfoList = phoneInfoList;
    }

    List<PhoneInfo> phoneInfoList;

    @Override
    public int getCount() {
        return phoneInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return phoneInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemViewBinding binding;
        if (convertView == null) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.list_item_view,
                    parent,
                    false);
            convertView = binding.getRoot();
        } else {
            binding = DataBindingUtil.getBinding(convertView);
        }
        binding.setItemData(phoneInfoList.get(position));

        return convertView;
    }
}

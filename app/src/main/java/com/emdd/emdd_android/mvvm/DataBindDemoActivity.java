package com.emdd.emdd_android.mvvm;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;

import com.emdd.emdd_android.R;
import com.emdd.emdd_android.databinding.ActivityDatabindingDemoBinding;
import com.emdd.emdd_android.mvvm.adapter.PhoneAdapter;
import com.emdd.emdd_android.mvvm.bean.EditValue;
import com.emdd.emdd_android.mvvm.bean.GoodsInfo;
import com.emdd.emdd_android.mvvm.bean.PhoneInfo;

import java.util.ArrayList;
import java.util.List;

/// @author chenwei
/// create at 2019-08-08
/// description
public class DataBindDemoActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityDatabindingDemoBinding binding;
    GoodsInfo goodsInfo;

    List<PhoneInfo> phoneInfoList;
    PhoneAdapter adapter;

    EditValue editValue;

    ObservableField<String> testStr = new ObservableField<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding_demo);
        goodsInfo = new GoodsInfo();
        binding.setGoods(goodsInfo);
        binding.setChangeClick(this);
        binding.setAddClick(this);
        editValue = new EditValue();
        binding.setEditValue(editValue);
        binding.setTestStr(testStr);

        phoneInfoList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            phoneInfoList.add(new PhoneInfo((i + 1000), String.format("item---%d", i + 1000)));
        }
        adapter = new PhoneAdapter(phoneInfoList);

        binding.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_change_goods:
                change();
                break;
            case R.id.btn_add:
                addNewItem();
                break;
        }
    }

    private void change() {
        Toast.makeText(this, "你点我了" + binding.tvGoodsId.getText(), Toast.LENGTH_SHORT).show();
        if (binding != null) {
            goodsInfo.setId(goodsInfo.getId() + 10);
            int len = phoneInfoList.size() - 1;
            if (len > 0) {
                int id = phoneInfoList.get(len).id.get();
                phoneInfoList.add(new PhoneInfo(id + 1, String.format("item---%d", id + 1000)));
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void addNewItem() {
        System.out.println("-----------> " + testStr.get());
        String newValue = testStr.get();

        if (TextUtils.isEmpty(newValue)) {
            Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        int len = phoneInfoList.size() - 1;
        if (len > 0) {
            int id = phoneInfoList.get(len).id.get();
            phoneInfoList.add(0, new PhoneInfo(id + 1, newValue));
            adapter.notifyDataSetChanged();
        }
    }
}

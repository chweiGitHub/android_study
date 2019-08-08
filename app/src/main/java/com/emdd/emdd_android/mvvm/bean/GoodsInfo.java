package com.emdd.emdd_android.mvvm.bean;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.emdd.emdd_android.BR;

public class GoodsInfo extends BaseObservable {

    public GoodsInfo() {
        id = 1001;
        name = "red mi";
    }

    private int id;
    private String name;

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @NonNull
    @Override
    public String toString() {
        return "id:"+id+"------name:"+name;
    }
}

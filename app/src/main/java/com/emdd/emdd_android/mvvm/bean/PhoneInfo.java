package com.emdd.emdd_android.mvvm.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;

import com.emdd.emdd_android.BR;

public class PhoneInfo {
    public PhoneInfo(int id, String name) {
        this.id.set(id);
        this.name.set(name);
    }

    public ObservableField<Integer> id = new ObservableField<>();
    public ObservableField<String> name = new ObservableField<>();
}

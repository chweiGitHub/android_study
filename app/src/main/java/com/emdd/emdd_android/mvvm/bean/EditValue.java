package com.emdd.emdd_android.mvvm.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.emdd.emdd_android.BR;

public class EditValue  extends BaseObservable {




    private  String editValue;

    @Bindable
    public String getEditValue() {
        return editValue;
    }

    public void setEditValue(String editValue) {
        this.editValue = editValue;
        notifyPropertyChanged(BR.editValue);
    }
}

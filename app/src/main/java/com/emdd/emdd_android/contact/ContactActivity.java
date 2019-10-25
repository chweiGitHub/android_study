package com.emdd.emdd_android.contact;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getContactList();
    }

    private void getContactList (){
        GetPhoneList utils = new GetPhoneList(this);
        List<PhoneDto> list  =  utils.getPhone();
        for (int i  =0;i< list.size();i++){
            System.out.println(list.get(i).name +": "+list.get(i).telPhone);
        }
    }

    public static class PhoneDto {
        private String name;        //联系人姓名
        private String telPhone;    //电话号码


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTelPhone() {
            return telPhone;
        }

        public void setTelPhone(String telPhone) {
            this.telPhone = telPhone;
        }

        public PhoneDto() {
        }

        public PhoneDto(String name, String telPhone) {
            this.name = name;
            this.telPhone = telPhone;
        }
    }

    public static class GetPhoneList {

        // 号码
        public final static String NUM = ContactsContract.CommonDataKinds.Phone.NUMBER;
        // 联系人姓名
        public final static String NAME = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;

        //上下文对象
        private Context context;
        //联系人提供者的uri
        private Uri phoneUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        public GetPhoneList(Context context) {
            this.context = context;
        }

        //获取所有联系人
        public List<PhoneDto> getPhone() {
            List<PhoneDto> phoneDtos = new ArrayList<>();
            ContentResolver cr = context.getContentResolver();
            Cursor cursor = cr.query(phoneUri, new String[]{NUM, NAME}, null, null, null);
            while (cursor.moveToNext()) {
                PhoneDto phoneDto = new PhoneDto(cursor.getString(cursor.getColumnIndex(NAME)), cursor.getString(cursor.getColumnIndex(NUM)));
                phoneDtos.add(phoneDto);
            }
            return phoneDtos;
        }
    }
}

package com.doozycod.getmaster;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceMethod {
    Context context;
    SharedPreferences sp;

    public SharedPreferenceMethod(Context context) {
        this.context = context;

    }

    public void saveId(String id) {
        sp = context.getSharedPreferences("getmaster_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editior = sp.edit();
        sp_editior.putString("id", id);
        sp_editior.commit();
    }

    public void saveUserName(String userName) {
        sp = context.getSharedPreferences("getmaster_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editior = sp.edit();
        sp_editior.putString("userName", userName);
        sp_editior.commit();
    }

    public String getId() {
        SharedPreferences sp = context.getSharedPreferences("getmaster_PREF", Context.MODE_PRIVATE);
        return sp.getString("id", "");
    }

    public String getUserName() {
        SharedPreferences sp = context.getSharedPreferences("getmaster_PREF", Context.MODE_PRIVATE);
        return sp.getString("userName", "");
    }

    void clearUser() {
        sp = context.getSharedPreferences("getmaster_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_editior = sp.edit();
        sp_editior.putString("userName", "").clear().commit();
    }


}

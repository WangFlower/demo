package com.example.momo.myapplication.demo.edittext;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;

/**
 * Created by MOMO on 17/1/23.
 */

public class User extends BaseObservable {

    private String username;
    private String psw;
    public User(){
        username = "sam";
        psw = "123";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Bindable
    public String getPsw() {
        return this.psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
        notifyPropertyChanged(BR.psw);
    }


}

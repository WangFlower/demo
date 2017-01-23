package com.example.momo.myapplication.demo.databinding;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.momo.myapplication.R;
import com.example.momo.myapplication.databinding.DataBindingBinding;
import com.example.momo.myapplication.demo.edittext.User;

/**
 * Created by MOMO on 17/1/23.
 */

public class DataBindingActivity extends Activity {

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingBinding binding =  DataBindingUtil.setContentView(this, R.layout.data_binding);
        user = new User();
        binding.setUser(user);
        handler.sendEmptyMessageDelayed(1,2000);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            user.setPsw("wwww");
            user.setUsername("sdsaafasdfdsa");
        }
    };
}

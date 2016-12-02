package com.example.momo.myapplication.mvp.model;

import com.example.momo.myapplication.mvp.bean.User;

/**
 * Created by MOMO on 16/10/14.
 */

public class UserModel implements IUserModel {
    @Override
    public boolean doLogin(User user) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public User getUser() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setUsername("sam");
        user.setPwd("123");
        return user;
    }
}

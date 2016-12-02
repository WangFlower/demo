package com.example.momo.myapplication.mvp.model;

import com.example.momo.myapplication.mvp.bean.User;

/**
 * Created by MOMO on 16/10/14.
 */

public interface IUserModel {

    boolean doLogin(User user);
    User getUser();
}

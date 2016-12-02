package com.example.momo.myapplication.mvp.presenter;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.example.momo.myapplication.mvp.bean.User;
import com.example.momo.myapplication.mvp.model.IUserModel;
import com.example.momo.myapplication.mvp.model.UserModel;
import com.example.momo.myapplication.mvp.view.MainView;
import com.example.momo.myapplication.demo.edittext.SamActivity;

/**
 * Created by MOMO on 16/10/14.
 */

public class ILoginPresenterImpl implements ILoginPresenter {

    MainView mView;
    IUserModel mIUserModel;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mView.onSetProgressBarVisiblity(View.GONE);
        }
    };

    public ILoginPresenterImpl(MainView view){
        this.mView = view;
        this.mIUserModel = new UserModel();
    }

    @Override
    public void clear() {
        mView.clearText();
    }

    @Override
    public void doLogin() {
        mView.onSetProgressBarVisiblity(View.VISIBLE);
        String userName = mView.getUserName();
        String pwd = mView.getPwd();
        final User user = new User();
        user.setUsername(userName);
        user.setPwd(pwd);
        new Thread(){
            @Override
            public void run() {
                Intent intent = new Intent(mView.getContext(), SamActivity.class);
                mView.getContext().startActivity(intent);
            }
        }.start();
    }



}

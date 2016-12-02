package com.example.momo.myapplication.mvp.view;

import android.content.Context;

/**
 * Created by MOMO on 16/10/14.
 */

public interface MainView {

    String getPwd();
    String getUserName();

    void clearText();

    void onSetProgressBarVisiblity(int visiblity);

    Context getContext();

}

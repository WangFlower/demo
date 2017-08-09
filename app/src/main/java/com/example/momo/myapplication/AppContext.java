package com.example.momo.myapplication;

import android.content.Context;

/**
 * Created by sam on 2017/8/1.
 */

public class AppContext {

    private static Context sContext;

    public static void setContext(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        return sContext;
    }
}

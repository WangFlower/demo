package com.example.momo.myapplication.demo.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by MOMO on 17/3/15.
 */

public class BroadcastreceiverDemo extends BroadcastReceiver {




    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("wangrenguang","onReceive");
    }
}

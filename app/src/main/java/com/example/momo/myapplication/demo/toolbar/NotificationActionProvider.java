package com.example.momo.myapplication.demo.toolbar;


import android.content.Context;
import android.support.v4.view.ActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.momo.myapplication.R;


/**
 * Created by wang.renguang on 16/11/16.
 */

public class NotificationActionProvider extends ActionProvider {

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public NotificationActionProvider(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.common_action_provider_notification, null, false);
        view.findViewById(R.id.menu_search_group).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("wangrenguang","NotificationActionProvider:"+v.getId());
            }
        });

        return view;
    }
}

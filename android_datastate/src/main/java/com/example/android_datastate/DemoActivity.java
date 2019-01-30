package com.example.android_datastate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.datastate_processor.State;

public class DemoActivity extends Activity {


    @State
    public int a = 0;
    @State
    public String name;
    @State
    public String[] names;
    @State
    private String priName;

    @State
    String defName;

    @State
    protected String proName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

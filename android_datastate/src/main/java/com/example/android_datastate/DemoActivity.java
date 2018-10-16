package com.example.android_datastate;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.example.datastate_processor.State;

public class DemoActivity extends Activity {


    @State
    int a = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

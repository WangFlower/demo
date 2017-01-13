package com.example.momo.myapplication.demo.edittext;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.example.momo.myapplication.R;

/**
 * Created by MOMO on 16/10/31.
 */

public class SamActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sam);
        findViewById(R.id.sam).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}

package com.example.momo.myapplication.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.momo.myapplication.R;
import com.example.momo.myapplication.demo.edittext.SamEdText;
import com.example.momo.myapplication.mvp.presenter.ILoginPresenter;
import com.example.momo.myapplication.mvp.presenter.ILoginPresenterImpl;

public class MainActivity extends AppCompatActivity implements MainView{

    Button mCommit;
    Button mCancel;

    SamEdText mUserName;
    EditText mPwd;

    ProgressBar mPro;
    ILoginPresenter mILoginPresenter;







    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mILoginPresenter = new ILoginPresenterImpl(this);
        setContentView(R.layout.activity_main);
        mCommit = (Button) this.findViewById(R.id.commit);
        mCancel = (Button) this.findViewById(R.id.cancel);
        mUserName = (SamEdText) this.findViewById(R.id.username);
        mPwd = (EditText) this.findViewById(R.id.pwd);
        mPro = (ProgressBar) this.findViewById(R.id.pro);

        mCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mILoginPresenter.doLogin();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mILoginPresenter.clear();
            }
        });

        Bundle bundle = mUserName.getInputExtras(true);
        bundle.putInt("SOGOU_EXPRESSION",1);

        mPwd.getInputExtras(true).putInt("SOGOU_EXPRESSION",1);

    }





    @Override
    public String getPwd() {
        return mPwd.getText().toString();
    }

    @Override
    public String getUserName() {
        return mUserName.getText().toString();
    }

    @Override
    public void clearText() {
        mUserName.setText("");
        mPwd.setText("");
    }

    @Override
    public void onSetProgressBarVisiblity(int visiblity) {
        this.mPro.setVisibility(visiblity);
    }

    @Override
    public Context getContext() {
        return this;
    }
}

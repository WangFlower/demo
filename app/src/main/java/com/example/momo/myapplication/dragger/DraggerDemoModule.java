package com.example.momo.myapplication.dragger;

import com.example.momo.myapplication.dragger.presenter.DraggerDemoPresenter;
import com.example.momo.myapplication.dragger.presenter.IDraggerDemoPresenter;
import com.example.momo.myapplication.dragger.view.DraggerDemoActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wang.renguang on 2018/5/28.
 * 给DraggerDemo提供实例
 */
@Module
public class DraggerDemoModule {

    @Provides
    DraggerDemoPresenter presenter(){
        return new DraggerDemoPresenter();
    }

}

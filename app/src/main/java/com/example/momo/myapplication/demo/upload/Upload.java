package com.example.momo.myapplication.demo.upload;

import java.io.File;

/**
 * create time 2018/11/13
 * by wangrenguang
 */
public class Upload {


    public Upload getInstance(){
        return Holder.instance;
    }

    private static class Holder{
        private static final Upload instance = new Upload();
    }

    private Upload(){

    }


    public void uploadFile(File file){

    }

}

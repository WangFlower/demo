package com.example.upload.task;

import java.util.Map;


/**
 * 上传task
 */
public class UploadTask extends Thread {

    private int id;

    private boolean needCancel = false;

    private boolean executed = false;

    private String fileName;

    private int position;

    private Map<String,String> params;

    private int errorCode;


    @Override
    public void run() {
        super.run();
    }



}

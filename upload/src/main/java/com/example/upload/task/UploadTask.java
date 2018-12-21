package com.example.upload.task;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Map;


/**
 * 上传task
 */
@Entity
public class UploadTask extends Thread {

    @Id
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

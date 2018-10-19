package com.example.upload.task;

import java.io.File;

/**
 * 上传文件分片
 */
public class UploadSliceTask extends Thread{

    private int id;

    private int index;

    private boolean needCancel = false;

    private boolean executed = false;

    private File file;

    private int start;

    private int offset;


    @Override
    public void run() {
        super.run();
    }
}

package com.example.upload;

public interface UploadListener {


    void onStart();

    void onProcess(float process);

    void onStop();

    void onError();


}

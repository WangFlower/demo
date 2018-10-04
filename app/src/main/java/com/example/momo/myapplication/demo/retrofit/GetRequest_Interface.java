package com.example.momo.myapplication.demo.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetRequest_Interface {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    Call<Translation> getCall();

    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    Call<Translation1> getCall1(@Field("i") String targetSentence);
}

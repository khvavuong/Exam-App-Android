package com.example.examapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static final String url = "http://192.168.1.97/TestExam/";
    private static RetrofitHelper clientObject;
    private static Retrofit retrofit;

    RetrofitHelper(){
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitHelper getInstance() {
        if (retrofit == null)
            clientObject = new RetrofitHelper();

        return clientObject;

    }
    QuestionApi getApi(){
        return retrofit.create(QuestionApi.class);
    }
}

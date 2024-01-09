package com.example.examapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuestionApi {
    @GET("getQuestion.php")
    Call<List<QuestionsListResponse>>getData();
}

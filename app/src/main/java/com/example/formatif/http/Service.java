package com.example.formatif.http;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service
{
    @GET("exam2022/motdepasse/{passe}")
    Call<String> checkPassword(@Path("passe") String passe);
}

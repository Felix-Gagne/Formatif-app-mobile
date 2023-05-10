package com.example.formatif.http;

import org.kickmyb.CustomGson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtil
{
    private static Service instance;

    public static Service get()
    {
        if (instance == null)
        { //  ca sera le cas au tout premier appel
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(CustomGson.getIt()))
                    .baseUrl("https://4n6.azurewebsites.net/")
                    .build();

            instance = retrofit.create(Service.class);
            return instance;
        } else {
            return instance;
        }
    }
}

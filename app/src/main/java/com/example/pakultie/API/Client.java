package com.example.pakultie.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
        private static Retrofit retrofit = null;
        private static final String BASE_URL = "http://www.mocky.io/";

        public static Retrofit getRetrofit() {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }

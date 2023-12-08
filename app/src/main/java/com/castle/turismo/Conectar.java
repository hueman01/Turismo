package com.castle.turismo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Conectar {
    public static Retrofit Backend(){
        return new Retrofit.Builder()
                .baseUrl("https://blae-cotton.000webhostapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
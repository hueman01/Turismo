package com.castle.turismo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface BackendRegiones {
    @GET("region/leer.php")
    Call<List<Regiones>> regiones();

    @POST("region/crear.php")
    Call<String> crear(@Body Regiones regiones);

    @PUT("region/actualizar.php")
    Call<String> actualizar(@Body Regiones regiones);
}

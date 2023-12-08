package com.castle.turismo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface BackendAtractivos {
    @GET("atractivo/leer.php")
    Call<List<Atractivos>> atractivos();

    @POST("atractivo/buscar.php")
    Call<List<Atractivos>> buscarporciudad(@Body Ciudades ciudades);

    @POST("atractivo/crear.php")
    Call<String> crear(@Body Atractivos atractivos);

    @PUT("atractivo/actualizar.php")
    Call<String> actualizar(@Body Atractivos atractivos);
}

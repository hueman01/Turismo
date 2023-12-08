package com.castle.turismo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface BackendCiudades {
    @GET("ciudad/leer.php")
    Call<List<Ciudades>> ciudades();

    @POST("ciudad/buscar.php")
    Call<List<Ciudades>> buscarporregion(@Body Ciudades ciudades);

    @POST("ciudad/crear.php")
    Call<String> crear(@Body Ciudades ciudades);

    @PUT("ciudad/actualizar.php")
    Call<String> actualizar(@Body Ciudades ciudades);
}

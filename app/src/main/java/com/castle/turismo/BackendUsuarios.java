package com.castle.turismo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BackendUsuarios {
    @GET("usuarios/leerusuarios.php")
    Call<List<Usuarios>> usuarios();

    @POST("usuarios/crearusuario.php")
    Call<String> crear(@Body Usuarios usuarios);

    @POST("usuarios/acceder.php")
    Call<Usuarios> acceder(@Body Usuarios usuarios);
}

package com.edisondeveloper.petagram.Modelo;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EndPointHeroku {

    @POST(Constantes.REGISTER_USER)
    Call<UsuarioFirebase> registerUser(@Body UsuarioFirebase user);

    @GET(Constantes.SEND_NOTIFICATION_LIKE)
    Call<Like> sendNotificationLike(@Path("idFoto") String idFoto, @Path("idUsuario") String idUsuario, @Path("token") String token);


}

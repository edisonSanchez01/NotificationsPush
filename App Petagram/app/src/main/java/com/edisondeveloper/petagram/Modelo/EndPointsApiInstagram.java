package com.edisondeveloper.petagram.Modelo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPointsApiInstagram {

    @GET(Constantes.URL_GET_MEDIA_USER)
    Call<MediaResponse> getMediaUser();

    @GET(Constantes.URL_GET_INFO_USER)
    Call<UserInstagram> getInfoUser();

}

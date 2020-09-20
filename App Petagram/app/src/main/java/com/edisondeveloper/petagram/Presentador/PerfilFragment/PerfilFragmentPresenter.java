package com.edisondeveloper.petagram.Presentador.PerfilFragment;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.edisondeveloper.petagram.Modelo.DataUtils;
import com.edisondeveloper.petagram.Modelo.Constantes;
import com.edisondeveloper.petagram.Modelo.EndPointHeroku;
import com.edisondeveloper.petagram.Modelo.EndPointsApiInstagram;
import com.edisondeveloper.petagram.Modelo.Like;
import com.edisondeveloper.petagram.Modelo.MediaDeserializador;
import com.edisondeveloper.petagram.Modelo.MediaResponse;
import com.edisondeveloper.petagram.Modelo.MediaUser;
import com.edisondeveloper.petagram.R;
import com.edisondeveloper.petagram.Vista.Fragments.IPerfilFragmentView;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerfilFragmentPresenter implements IPerfilFragmentPresenter {

    private Context context;
    private IPerfilFragmentView iPerfilFragmentView;
    private ArrayList<MediaUser> listRecyclerView;

    public PerfilFragmentPresenter(Context context, IPerfilFragmentView iPerfilFragmentView) {
        this.context = context;
        this.iPerfilFragmentView = iPerfilFragmentView;
    }

    public void consumeServiceWeb(){
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(MediaResponse.class, new MediaDeserializador());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL_API_INSTAGRAM)
                .addConverterFactory(GsonConverterFactory.create(gson.create()))
                .build();
        EndPointsApiInstagram endPointsApiInstagram = retrofit.create(EndPointsApiInstagram.class);
        Call<MediaResponse> response = endPointsApiInstagram.getMediaUser();
        response.enqueue(new Callback<MediaResponse>() {
            @Override
            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {
                MediaResponse mediaResponse = response.body();
                listRecyclerView = mediaResponse.getListMediaUser();
                iPerfilFragmentView.generarGridLayout();
                iPerfilFragmentView.configAdapter(listRecyclerView);
            }

            @Override
            public void onFailure(Call<MediaResponse> call, Throwable t) {
                 Log.i("Error en la respuesta", t.toString());
            }
        });
    }

    @Override
    public void darLike(int position) {
        if(DataUtils.idUser != null && DataUtils.tokenDivice != null){
            MediaUser currentImage = listRecyclerView.get(position);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constantes.BASE_URL_API_HEROKU)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            EndPointHeroku request = retrofit.create(EndPointHeroku.class);
            Call<Like> response = request.sendNotificationLike(currentImage.getId(), DataUtils.idUser, DataUtils.tokenDivice);
            response.enqueue(new Callback<Like>() {
                @Override
                public void onResponse(Call<Like> call, Response<Like> response) {
                    Toast.makeText(context, context.getString(R.string.like), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Like> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
}

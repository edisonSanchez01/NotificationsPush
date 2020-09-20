package com.edisondeveloper.petagram.Modelo;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataUtils {

    public static String tokenDivice = null;
    public static String idUser = null;

    public static void getTokenDevice(){
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                tokenDivice = task.getResult().getToken();
            }
        });
    }

    public static void getIdUserInstagram(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.BASE_URL_API_INSTAGRAM)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EndPointsApiInstagram client =  retrofit.create(EndPointsApiInstagram.class);
        Call<UserInstagram> request = client.getInfoUser();
        request.enqueue(new Callback<UserInstagram>() {
            @Override
            public void onResponse(Call<UserInstagram> call, Response<UserInstagram> response) {
                idUser = response.body().getId();
            }

            @Override
            public void onFailure(Call<UserInstagram> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}

package com.edisondeveloper.petagram.Presentador.MainActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.edisondeveloper.petagram.Modelo.DataUtils;
import com.edisondeveloper.petagram.Modelo.Constantes;
import com.edisondeveloper.petagram.Modelo.ContratoTopFive;
import com.edisondeveloper.petagram.Modelo.EndPointHeroku;
import com.edisondeveloper.petagram.Modelo.EndPointsApiInstagram;
import com.edisondeveloper.petagram.Modelo.Mascota;
import com.edisondeveloper.petagram.Modelo.UserInstagram;
import com.edisondeveloper.petagram.Modelo.UsuarioFirebase;
import com.edisondeveloper.petagram.R;
import com.edisondeveloper.petagram.Vista.Fragments.MainFragment;
import com.edisondeveloper.petagram.Vista.Fragments.PerfilFragment;
import com.edisondeveloper.petagram.Vista.MainActivity.IMainActivityView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityPresenter implements IMainActivityPresenter {

    private IMainActivityView iMainActivityView;
    private Context context;

    public MainActivityPresenter(IMainActivityView iMainActivityView, Context context){
        this.context = context;
        this.iMainActivityView = iMainActivityView;
        DataUtils.getIdUserInstagram();
        DataUtils.getTokenDevice();
    }

    @Override
    public void generateTopFive(ArrayList<Mascota> listPets, ContentResolver resolver) {
        Collections.sort(listPets, new Comparator<Mascota>() {
            @Override
            public int compare(Mascota mascota, Mascota t1) {
                return Integer.valueOf(t1.getPuntuacion()).compareTo(mascota.getPuntuacion());
            }
        });

        Cursor cursor = resolver.query(ContratoTopFive.TablaTopFive.URI_TABLA_TOP_FIVE, new String[]{ContratoTopFive.TablaTopFive.COLUMN_ID, ContratoTopFive.TablaTopFive.COLUMN_NAME,
                ContratoTopFive.TablaTopFive.COLUMN_IMAGE, ContratoTopFive.TablaTopFive.COLUMN_RATING}, null, null, null);

        if(cursor.moveToNext()){
            resolver.delete(ContratoTopFive.TablaTopFive.URI_TABLA_TOP_FIVE, null, null);
        }
        cursor.close();
        for(int i=0; i<5; i++){
            Mascota mascotaTop = listPets.get(i);
            ContentValues values = new ContentValues();
            values.put(ContratoTopFive.TablaTopFive.COLUMN_NAME, mascotaTop.getNombre());
            values.put(ContratoTopFive.TablaTopFive.COLUMN_IMAGE, mascotaTop.getImage());
            values.put(ContratoTopFive.TablaTopFive.COLUMN_RATING, mascotaTop.getPuntuacion());
            resolver.insert(ContratoTopFive.TablaTopFive.URI_TABLA_TOP_FIVE, values);
        }
    }

    @Override
    public void sendTokenToServer() {
        if(DataUtils.idUser != null && DataUtils.tokenDivice != null){
            Retrofit retrofit = new  Retrofit.Builder()
                    .baseUrl(Constantes.BASE_URL_API_HEROKU)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            EndPointHeroku client = retrofit.create(EndPointHeroku.class);
            UsuarioFirebase user = new UsuarioFirebase(DataUtils.idUser, DataUtils.tokenDivice);
            Call<UsuarioFirebase> request = client.registerUser(user);
            request.enqueue(new Callback<UsuarioFirebase>() {
                @Override
                public void onResponse(Call<UsuarioFirebase> call, Response<UsuarioFirebase> response) {
                    Toast.makeText(context, context.getString(R.string.registration_successfull), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<UsuarioFirebase> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

    }

    @Override
    public void generateListFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new PerfilFragment());
        iMainActivityView.configViewPagerAdapter(fragments);
    }
}

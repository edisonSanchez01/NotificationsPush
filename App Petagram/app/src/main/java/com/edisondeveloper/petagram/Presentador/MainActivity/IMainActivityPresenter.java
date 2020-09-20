package com.edisondeveloper.petagram.Presentador.MainActivity;

import android.content.ContentResolver;

import com.edisondeveloper.petagram.Modelo.Mascota;

import java.util.ArrayList;

public interface IMainActivityPresenter {

    void generateTopFive(ArrayList<Mascota> listPets, ContentResolver resolver);

    void generateListFragments();

    void sendTokenToServer();

}

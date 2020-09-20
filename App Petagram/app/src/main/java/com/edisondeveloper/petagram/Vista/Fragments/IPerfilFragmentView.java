package com.edisondeveloper.petagram.Vista.Fragments;

import com.edisondeveloper.petagram.Modelo.MediaUser;

import java.util.ArrayList;

public interface IPerfilFragmentView {

    void generarGridLayout();
    void configAdapter(ArrayList<MediaUser> list);

}

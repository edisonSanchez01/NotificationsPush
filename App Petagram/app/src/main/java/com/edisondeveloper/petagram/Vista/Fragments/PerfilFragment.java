package com.edisondeveloper.petagram.Vista.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edisondeveloper.petagram.Modelo.Adapters.PerfilAdapter;
import com.edisondeveloper.petagram.Modelo.Constantes;
import com.edisondeveloper.petagram.Modelo.MediaUser;
import com.edisondeveloper.petagram.Presentador.PerfilFragment.PerfilFragmentPresenter;
import com.edisondeveloper.petagram.R;

import java.util.ArrayList;

public class PerfilFragment extends Fragment implements IPerfilFragmentView, PerfilAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private TextView namePerfil;
    private PerfilFragmentPresenter perfilFragmentPresenter;

    public PerfilFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfil_fragment, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewPerfil);
        namePerfil = view.findViewById(R.id.name_perfil);
        namePerfil.setText(Constantes.getNameUser(getActivity()));
        perfilFragmentPresenter = new PerfilFragmentPresenter(getActivity(), this);
        perfilFragmentPresenter.consumeServiceWeb();
        return view;
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void configAdapter(ArrayList<MediaUser> list) {
        recyclerView.setHasFixedSize(true);
        PerfilAdapter adapter = new PerfilAdapter(getActivity(), list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        perfilFragmentPresenter.darLike(position);
    }

}
package com.edisondeveloper.petagram.Modelo.Adapters;

import android.content.Context;
import com.edisondeveloper.petagram.Modelo.Mascota;
import com.edisondeveloper.petagram.R;

import java.util.ArrayList;

public class SingletonListPets {

    private ArrayList<Mascota> listPets;
    private static SingletonListPets singletonListPets;

    public static SingletonListPets getListPets(Context context){
        if(singletonListPets == null){
            singletonListPets = new SingletonListPets(context);
        }
        return singletonListPets;
    }

    private SingletonListPets(Context context){
        listPets = new ArrayList<>();
        listPets.add(new Mascota(context.getString(R.string.mascot01),R.drawable.mascotas_img_01,3));
        listPets.add(new Mascota(context.getString(R.string.mascot02),R.drawable.mascotas_img_02, 5));
        listPets.add(new Mascota(context.getString(R.string.mascot03),R.drawable.mascotas_img_03,6));
        listPets.add(new Mascota(context.getString(R.string.mascot04),R.drawable.mascotas_img_04, 2));
        listPets.add(new Mascota(context.getString(R.string.mascot05),R.drawable.mascotas_img_05, 1));
        listPets.add(new Mascota(context.getString(R.string.mascot06),R.drawable.mascotas_img_06, 9));
        listPets.add(new Mascota(context.getString(R.string.mascot07),R.drawable.mascotas_img_07, 7));
        listPets.add(new Mascota(context.getString(R.string.mascot08),R.drawable.mascotas_img_08, 10));
        listPets.add(new Mascota(context.getString(R.string.mascot09),R.drawable.mascotas_img_09, 8));
        listPets.add(new Mascota(context.getString(R.string.mascot10),R.drawable.mascotas_img_10,5));
    }

    public ArrayList<Mascota> getListPets() {
        return listPets;
    }
}

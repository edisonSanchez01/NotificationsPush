package com.edisondeveloper.petagram.Modelo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edisondeveloper.petagram.Modelo.Mascota;
import com.edisondeveloper.petagram.R;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {

    private ArrayList<Mascota> listPets;
    private Context context;

    public MascotaAdapter(Context context, ArrayList<Mascota> list){
        this.listPets = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_petagram, parent, false);
        return new MascotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder holder, int position) {
        final Mascota currentPet = listPets.get(position);
        holder.imagePet.setImageResource(currentPet.getImage());
        holder.namePet.setText(currentPet.getNombre());
        holder.calificacion.setText(String.valueOf(currentPet.getPuntuacion()));
        holder.btnCalificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPet.setPuntuacion(currentPet.getPuntuacion() + 1);
                holder.calificacion.setText(String.valueOf(currentPet.getPuntuacion()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPets.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagePet;
        private ImageButton btnCalificar;
        private TextView namePet;
        private TextView calificacion;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePet = itemView.findViewById(R.id.image_pet);
            btnCalificar = itemView.findViewById(R.id.btnCalificar);
            namePet = itemView.findViewById(R.id.txtNamePet);
            calificacion = itemView.findViewById(R.id.puntuacion);
        }
    }
}

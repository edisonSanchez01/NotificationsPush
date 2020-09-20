package com.edisondeveloper.petagram.Modelo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edisondeveloper.petagram.Modelo.Constantes;
import com.edisondeveloper.petagram.Modelo.MediaUser;
import com.edisondeveloper.petagram.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PerfilAdapter extends RecyclerView.Adapter<PerfilAdapter.PerfilViewHolder> {

    private ArrayList<MediaUser> listPhotos;
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    public PerfilAdapter(Context context, ArrayList<MediaUser> list, OnItemClickListener onItemClickListener){
        listPhotos = list;
        this.context = context;
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public PerfilViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.foto_perfil, parent, false);
        return new PerfilViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PerfilViewHolder holder, int position) {
        MediaUser mediaUser = listPhotos.get(position);
        if(!mediaUser.getMediaType().equals(Constantes.MEDIA_VIDEO) && !mediaUser.getMediaType().equals(Constantes.MEDIA_ALBUM)){
            Picasso.get().load(mediaUser.getMediaUrl()).placeholder(R.drawable.matias01).into(holder.imagen);
        }

    }

    @Override
    public int getItemCount() {
        return listPhotos.size();
    }

    public class PerfilViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imagen;

        public PerfilViewHolder(View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnItemClickListener.onItemClick(getAdapterPosition());
        }
    }

}

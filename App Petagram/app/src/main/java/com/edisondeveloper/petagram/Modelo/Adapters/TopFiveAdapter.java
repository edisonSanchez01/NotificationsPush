package com.edisondeveloper.petagram.Modelo.Adapters;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edisondeveloper.petagram.Modelo.ContratoTopFive;
import com.edisondeveloper.petagram.R;

public class TopFiveAdapter extends RecyclerView.Adapter<TopFiveAdapter.ViewHolderTopFive> {

    private Context context;
    private CursorAdapter cursorAdapter;
    private ViewHolderTopFive viewHolderTopFive;
    private int puntuacion;
    private String name;
    private int idImage;

    public TopFiveAdapter(Context context, Cursor cursor){
        this.context = context;
        this.cursorAdapter = new CursorAdapter(context,cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                View view = LayoutInflater.from(context).inflate(R.layout.item_list_petagram, parent, false);
                return view;
            }

            @Override
            public void bindView(View view, final Context context, final Cursor cursor) {
                name = cursor.getString(cursor.getColumnIndex(ContratoTopFive.TablaTopFive.COLUMN_NAME));
                viewHolderTopFive.name.setText(name);
                idImage = cursor.getInt(cursor.getColumnIndex(ContratoTopFive.TablaTopFive.COLUMN_IMAGE));
                viewHolderTopFive.image.setImageResource(idImage);
                puntuacion = cursor.getInt(cursor.getColumnIndex(ContratoTopFive.TablaTopFive.COLUMN_RATING));
                viewHolderTopFive.puntuaction.setText(String.valueOf(puntuacion));
            }
        };
    }

    public void changeCursor(Cursor cursor){
        cursorAdapter.swapCursor(cursor);
    }

    @NonNull
    @Override
    public ViewHolderTopFive onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = cursorAdapter.newView(context, cursorAdapter.getCursor(), parent);
        viewHolderTopFive = new ViewHolderTopFive(view);
        return viewHolderTopFive;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderTopFive holder, final int position) {
        cursorAdapter.getCursor().moveToPosition(position);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                puntuacion = Integer.parseInt(holder.puntuaction.getText().toString()) + 1;
                holder.puntuaction.setText(String.valueOf(puntuacion));
                ContentValues values = new ContentValues();
                values.put(ContratoTopFive.TablaTopFive.COLUMN_NAME, name);
                values.put(ContratoTopFive.TablaTopFive.COLUMN_IMAGE, idImage);
                values.put(ContratoTopFive.TablaTopFive.COLUMN_RATING, puntuacion);
                context.getContentResolver().update(ContentUris.withAppendedId(ContratoTopFive.TablaTopFive.URI_TABLA_TOP_FIVE, position),
                        values, null, null);
            }
        });
        cursorAdapter.bindView(viewHolderTopFive.itemView, context, cursorAdapter.getCursor());
    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }

    public static class ViewHolderTopFive extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;
        ImageButton button;
        TextView puntuaction;

        public ViewHolderTopFive(View view){
            super(view);
            image = view.findViewById(R.id.image_pet);
            name = view.findViewById(R.id.txtNamePet);
            button = view.findViewById(R.id.btnCalificar);
            puntuaction = view.findViewById(R.id.puntuacion);
        }
    }

}

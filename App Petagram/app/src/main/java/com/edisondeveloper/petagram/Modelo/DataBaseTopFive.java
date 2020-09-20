package com.edisondeveloper.petagram.Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseTopFive extends SQLiteOpenHelper {

    public DataBaseTopFive(Context context){
        super(context, ContratoTopFive.DATA_BASE_NAME, null, ContratoTopFive.VERSION_DATA_BASE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        creatingDataBase(db);
    }

    public void creatingDataBase(SQLiteDatabase db){
        String creating_data_base = "CREATE TABLE " + ContratoTopFive.TablaTopFive.TABLE_NAME + " (" +
                ContratoTopFive.TablaTopFive.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ContratoTopFive.TablaTopFive.COLUMN_NAME + " TEXT NOT NULL, " +
                ContratoTopFive.TablaTopFive.COLUMN_IMAGE + " INTEGER NOT NULL, " +
                ContratoTopFive.TablaTopFive.COLUMN_RATING + " INTEGER NOT NULL);";

        db.execSQL(creating_data_base);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ContratoTopFive.DATA_BASE_NAME);
        creatingDataBase(db);
    }

}

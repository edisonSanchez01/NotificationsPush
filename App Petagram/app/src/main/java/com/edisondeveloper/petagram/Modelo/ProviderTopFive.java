package com.edisondeveloper.petagram.Modelo;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProviderTopFive extends ContentProvider {

    private DataBaseTopFive dataBaseTopFive;
    private static final int TABLE_CODE = 100;
    private static final int ITEM_CODE = 101;
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(ContratoTopFive.CONTENT_AUTHORYTY, ContratoTopFive.TablaTopFive.TABLE_NAME, TABLE_CODE);
        URI_MATCHER.addURI(ContratoTopFive.CONTENT_AUTHORYTY, ContratoTopFive.TablaTopFive.TABLE_NAME + "/#", ITEM_CODE);
    }

    @Override
    public boolean onCreate() {
        dataBaseTopFive = new DataBaseTopFive(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase database = dataBaseTopFive.getReadableDatabase();
        Cursor resultado = null;
        int match = URI_MATCHER.match(uri);
        switch(match){
            case TABLE_CODE:
                resultado = database.query(ContratoTopFive.TablaTopFive.TABLE_NAME, projection, selection, selectionArgs, null , null, null);
                break;
            case ITEM_CODE:
                selection = ContratoTopFive.TablaTopFive.COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                resultado = database.query(ContratoTopFive.TablaTopFive.TABLE_NAME, projection, selection, selectionArgs, null, null, null, null);
                break;
        }
        resultado.setNotificationUri(getContext().getContentResolver(), uri);
        return resultado;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match){
            case TABLE_CODE:
                return ContratoTopFive.CONTENT_LIST_TYPE;
            case ITEM_CODE:
                return ContratoTopFive.CONTENT_ITEM_TYPE;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase database = dataBaseTopFive.getWritableDatabase();
        int match = URI_MATCHER.match(uri);
        long idFila = -1;
        if(match == TABLE_CODE){
            idFila = database.insert(ContratoTopFive.TablaTopFive.TABLE_NAME, null, values);
        }else{
            Toast.makeText(getContext(),"No se pudo insertar el registro", Toast.LENGTH_SHORT).show();
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri,idFila);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = dataBaseTopFive.getWritableDatabase();
        int match = URI_MATCHER.match(uri);
        int rowsDeleted = 0;
        switch (match){
            case TABLE_CODE:
                rowsDeleted = database.delete(ContratoTopFive.TablaTopFive.TABLE_NAME, selection, selectionArgs);
                break;
            case ITEM_CODE:
                selection = ContratoTopFive.TablaTopFive.COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = database.delete(ContratoTopFive.TablaTopFive.TABLE_NAME, selection, selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase database = dataBaseTopFive.getWritableDatabase();
        int rowsUpdated = 0;
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case TABLE_CODE:
                rowsUpdated = database.update(ContratoTopFive.TablaTopFive.TABLE_NAME, values, selection, selectionArgs);
                break;
            case ITEM_CODE:
                selection = ContratoTopFive.TablaTopFive.COLUMN_ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                rowsUpdated = database.update(ContratoTopFive.TablaTopFive.TABLE_NAME, values, selection, selectionArgs);
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}

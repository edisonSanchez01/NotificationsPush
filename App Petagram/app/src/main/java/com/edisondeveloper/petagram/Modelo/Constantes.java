package com.edisondeveloper.petagram.Modelo;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import com.edisondeveloper.petagram.R;

public class Constantes {

    public static final String BASE_URL_API_HEROKU = "https://radiant-basin-38198.herokuapp.com/";
    public static final String REGISTER_USER = "registrarUsuario";
    public static final String SEND_NOTIFICATION_LIKE = "enviarNotificacion/{idFoto}/{idUsuario}/{token}";
    public static final String NAME_USER = "name_user_perfil";
    public static final String BASE_URL_API_INSTAGRAM = "https://graph.instagram.com/";
    public static final String END_POINT_MEDIA = "me/media";
    public static final String END_POINT_USER = "me";
    public static final String USER_NAME = "username";
    public static final String FIELDS = "?fields=";
    public static final String MEDIA_VIDEO = "VIDEO";
    public static final String MEDIA_ALBUM = "CAROUSEL_ALBUM";
    public static final String NAME_JSON_ARRAY = "data";
    public static final String NAME_ID = "id";
    public static final String NAME_MEDIA_TYPE = "media_type";
    public static final String NAME_MEDIA_URL = "media_url";
    public static final String GET_MEDIA_USER = "id,media_type,media_url";
    public static final String GET_INFO_USER = "id,username";
    public static final String PARAM_ACCESS_TOKEN = "&access_token=";
    public static final String ACCESS_TOKEN = "IGQVJXV0RLQV9wa3FYUkt4Wk5pUURLX1pRUjFtT2w1QjBtVlNiZAGtZAcWVWUjQ2cU45ODY0WXRiVWNCMGpLOFNKaHVCaU9TZAmZAxYURBMzF3aENqSUNmUFhJTWJubjQzS0YxQUU1ZAHRB";
    public static final String URL_GET_INFO_USER = END_POINT_USER + FIELDS + GET_INFO_USER + PARAM_ACCESS_TOKEN + ACCESS_TOKEN;
    public static final String URL_GET_MEDIA_USER = END_POINT_MEDIA + FIELDS + GET_MEDIA_USER + PARAM_ACCESS_TOKEN + ACCESS_TOKEN;

    public static void setNameUser(Context context, String nameUser){
        SharedPreferences.Editor preNameUser = PreferenceManager.getDefaultSharedPreferences(context).edit();
        preNameUser.putString(NAME_USER, nameUser);
        preNameUser.apply();
    }

    public static String getNameUser(Context context){
        SharedPreferences preNameUser = PreferenceManager.getDefaultSharedPreferences(context);
        return preNameUser.getString(NAME_USER, context.getString(R.string.prev_name));
    }


}

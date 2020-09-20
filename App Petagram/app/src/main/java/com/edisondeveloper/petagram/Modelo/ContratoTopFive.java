package com.edisondeveloper.petagram.Modelo;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class ContratoTopFive {

    public static final String DATA_BASE_NAME = "TopfiveDataBase";
    public static int VERSION_DATA_BASE = 1;
    public static final String CONTENT_AUTHORYTY = "com.edisondeveloper.petagram";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORYTY);
    public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORYTY + "/" + DATA_BASE_NAME;
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORYTY + "/" + DATA_BASE_NAME;

    public static class TablaTopFive implements BaseColumns {

        public static final Uri URI_TABLA_TOP_FIVE = Uri.withAppendedPath(BASE_CONTENT_URI, TablaTopFive.TABLE_NAME);
        public static final String TABLE_NAME = "TopFive";

        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_IMAGE = "Image";
        public static final String COLUMN_RATING = "Rating";

    }

}

package com.edisondeveloper.petagram.Modelo;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MediaDeserializador implements JsonDeserializer<MediaResponse> {

    @Override
    public MediaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MediaResponse mediaResponse = gson.fromJson(json, MediaResponse.class);
        ArrayList<MediaUser> listMeda = new ArrayList<>();
        JsonArray jsonArray = json.getAsJsonObject().getAsJsonArray(Constantes.NAME_JSON_ARRAY);
        for(int i=0; i<jsonArray.size(); i++){
            JsonObject currentObject = jsonArray.get(i).getAsJsonObject();
            String id = currentObject.get(Constantes.NAME_ID).getAsString();
            String mediaType = currentObject.get(Constantes.NAME_MEDIA_TYPE).getAsString();
            String mediaUrl = currentObject.get(Constantes.NAME_MEDIA_URL).getAsString();
            MediaUser currentUser = new MediaUser(id, mediaType, mediaUrl);
            listMeda.add(currentUser);
        }
        mediaResponse.setListMediaUser(listMeda);
        return mediaResponse;
    }
}

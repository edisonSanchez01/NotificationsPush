package com.edisondeveloper.petagram.Modelo;

public class MediaUser {

    private String id;
    private String mediaType;
    private String mediaUrl;

    public MediaUser(String id, String mediaType, String mediaUrl) {
        this.id = id;
        this.mediaType = mediaType;
        this.mediaUrl = mediaUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

}

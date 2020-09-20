package com.edisondeveloper.petagram.Modelo;

public class UsuarioFirebase {

    private String id;
    private String tokenDevice;

    public UsuarioFirebase(String id, String tokenDevice){
        this.id = id;
        this.tokenDevice = tokenDevice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTokenDevice() {
        return tokenDevice;
    }

    public void setTokenDevice(String tokenDevice) {
        this.tokenDevice = tokenDevice;
    }
}

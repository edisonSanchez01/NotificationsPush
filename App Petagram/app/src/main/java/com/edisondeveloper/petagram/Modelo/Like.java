package com.edisondeveloper.petagram.Modelo;

public class Like {

    private String idFoto;
    private String idUsuario;
    private String token;

    public Like(String idFoto, String idUsuario, String token){
        this.idFoto = idFoto;
        this.idUsuario = idUsuario;
        this.token = token;
    }

    public String getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(String idFoto) {
        this.idFoto = idFoto;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

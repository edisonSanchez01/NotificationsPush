package com.edisondeveloper.petagram.Modelo;

public class PerfilMascota {

    private int image;
    private int puntaje;

    public PerfilMascota(int image, int puntaje) {
        this.image = image;
        this.puntaje = puntaje;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}

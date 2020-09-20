package com.edisondeveloper.petagram.Modelo;

import android.os.Parcel;
import android.os.Parcelable;

public class Mascota implements Parcelable {

    private String nombre;
    private int image;
    private int puntuacion;

    public Mascota (String nombre, int image, int puntuacion) {
        this.nombre = nombre;
        this.image = image;
        this.puntuacion = puntuacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    protected Mascota(Parcel in) {
        nombre = in.readString();
        image = in.readInt();
        puntuacion = in.readInt();
    }

    public static final Creator<Mascota> CREATOR = new Creator<Mascota>() {
        @Override
        public Mascota createFromParcel(Parcel in) {
            return new Mascota(in);
        }

        @Override
        public Mascota[] newArray(int size) {
            return new Mascota[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeInt(image);
        parcel.writeInt(puntuacion);
    }

}

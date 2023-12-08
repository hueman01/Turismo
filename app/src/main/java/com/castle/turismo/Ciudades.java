package com.castle.turismo;

import androidx.annotation.NonNull;

public class Ciudades {
    private int id;
    private String nombreciudad;
    private int idregion;
    private String nombreregion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreciudad() {
        return nombreciudad;
    }

    public void setNombreciudad(String nombreciudad) {
        this.nombreciudad = nombreciudad;
    }

    public int getIdregion() {
        return idregion;
    }

    public void setIdregion(int idregion) {
        this.idregion = idregion;
    }

    public String getNombreregion() {
        return nombreregion;
    }

    public void setNombreregion(String nombreregion) {
        this.nombreregion = nombreregion;
    }

    @NonNull
    @Override
    public String toString() {
        return nombreciudad;
    }
}

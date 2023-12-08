package com.castle.turismo;

import androidx.annotation.NonNull;

public class Regiones {
    private int id;
    private String nombreregion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return nombreregion;
    }
}

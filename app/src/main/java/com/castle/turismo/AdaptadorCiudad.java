package com.castle.turismo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorCiudad extends RecyclerView.Adapter<AdaptadorCiudad.RegionHolder> {

    private List<Ciudades> listado;

    public AdaptadorCiudad(List<Ciudades> listado) {
        this.listado = listado;
    }

    @NonNull
    @Override
    public RegionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RegionHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_ciudad, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RegionHolder holder, int position) {
        holder.textNombre.setText("CIUDAD: " + listado.get(position).getNombreciudad());
        holder.textRegion.setText("REGION: " + listado.get(position).getNombreregion());
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    public static class RegionHolder extends RecyclerView.ViewHolder {
        private TextView textNombre, textRegion;

        public RegionHolder(@NonNull View itemView) {
            super(itemView);

            textNombre = itemView.findViewById(R.id.textNombre);
            textRegion = itemView.findViewById(R.id.textRegion);
        }
    }
}

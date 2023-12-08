package com.castle.turismo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorRegion extends RecyclerView.Adapter<AdaptadorRegion.RegionHolder> {

    private List<Regiones> listado;

    public AdaptadorRegion(List<Regiones> listado) {
        this.listado = listado;
    }

    @NonNull
    @Override
    public RegionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RegionHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_vista, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RegionHolder holder, int position) {
        holder.textNombre.setText(listado.get(position).getNombreregion());
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    public static class RegionHolder extends RecyclerView.ViewHolder{
        private TextView textNombre;
        public RegionHolder(@NonNull View itemView) {
            super(itemView);

            textNombre = itemView.findViewById(R.id.textNombre);
        }
    }
}

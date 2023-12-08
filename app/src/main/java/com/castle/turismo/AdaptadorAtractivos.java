package com.castle.turismo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorAtractivos extends RecyclerView.Adapter<AdaptadorAtractivos.RegionHolder> {

    private IOnClick iOnClick;
    private List<Atractivos> listado;

    public interface IOnClick {
        void iconClick(Atractivos atractivos);
    }

    public AdaptadorAtractivos(List<Atractivos> listado, IOnClick _iOnClick) {
        this.listado = listado;
        this.iOnClick = _iOnClick;
    }

    @NonNull
    @Override
    public RegionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RegionHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo_atractivo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RegionHolder holder, int position) {
        holder.textNombre.setText(listado.get(position).getNombre());
        holder.textDescripcion.setText(listado.get(position).getDescripcion());
        holder.textLatLog.setText(listado.get(position).getLatitud() + "," + listado.get(position).getLongitud());

        Picasso.get().load(listado.get(position).getFoto())
                .resize(100, 100)
                .centerCrop().into(holder.imageView);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iOnClick.iconClick(listado.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    public static class RegionHolder extends RecyclerView.ViewHolder {
        private TextView textNombre, textDescripcion, textLatLog;
        private ImageView imageView;
        private ImageButton button;

        public RegionHolder(@NonNull View itemView) {
            super(itemView);

            textNombre = itemView.findViewById(R.id.textNombre);
            textDescripcion = itemView.findViewById(R.id.textDescripcion);
            textLatLog = itemView.findViewById(R.id.textLatAlt);
            imageView = itemView.findViewById(R.id.imageView);
            button = itemView.findViewById(R.id.imageButton);
        }
    }
}

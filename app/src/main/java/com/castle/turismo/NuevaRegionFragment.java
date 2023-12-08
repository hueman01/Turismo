package com.castle.turismo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NuevaRegionFragment extends Fragment {
    private EditText editNombre;
    private Button button;

    public NuevaRegionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nueva_region, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editNombre = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editNombre.getText().toString().trim().length() ==0)
                    return;

                Regiones regiones =new Regiones();
                regiones.setNombreregion(editNombre.getText().toString().trim());

                BackendRegiones backendRegiones= Conectar.Backend().create(BackendRegiones.class);
                backendRegiones.crear(regiones).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(requireContext(), response.body().toString(), Toast.LENGTH_LONG).show();
                            editNombre.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
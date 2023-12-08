package com.castle.turismo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevaCiudadFragment extends Fragment {
    private EditText editNombre;
    private Button button;
    private Spinner spinner;
    private int idregion = 0;

    public NuevaCiudadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nueva_ciudad, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editNombre = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.button);
        spinner = view.findViewById(R.id.spinner);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editNombre.getText().toString().trim().length() == 0 || idregion == 0)
                    return;

                Ciudades ciudades = new Ciudades();
                ciudades.setNombreciudad(editNombre.getText().toString().trim());
                ciudades.setIdregion(idregion);

                BackendCiudades backendCiudades = Conectar.Backend().create(BackendCiudades.class);
                backendCiudades.crear(ciudades).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
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

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idregion = ((Regiones) adapterView.getItemAtPosition(i)).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        BackendRegiones backendRegiones = Conectar.Backend().create(BackendRegiones.class);
        backendRegiones.regiones().enqueue(new Callback<List<Regiones>>() {
            @Override
            public void onResponse(Call<List<Regiones>> call, Response<List<Regiones>> response) {
                if (response.isSuccessful())
                    spinner.setAdapter(
                            new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, response.body())
                    );
            }

            @Override
            public void onFailure(Call<List<Regiones>> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
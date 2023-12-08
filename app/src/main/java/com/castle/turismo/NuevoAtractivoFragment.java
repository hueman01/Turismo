package com.castle.turismo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NuevoAtractivoFragment extends Fragment {
    private EditText editNombre, editDescripcion, editLatitud, editLogitud, editFoto;
    private Button button;
    private Spinner spinnerRegion, spinnerCiudad;
    private int idregion = 0, idCiudad = 0;

    public NuevoAtractivoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nuevo_atractivo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editNombre = view.findViewById(R.id.editNombre);
        editDescripcion = view.findViewById(R.id.editDescripcion);
        editLatitud = view.findViewById(R.id.editLatitud);
        editLogitud = view.findViewById(R.id.editLaongitud);
        editFoto = view.findViewById(R.id.editFoto);
        button = view.findViewById(R.id.button);
        spinnerRegion = view.findViewById(R.id.spinnerRegion);
        spinnerCiudad = view.findViewById(R.id.spinnerCiudad);

        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idregion = ((Regiones) adapterView.getItemAtPosition(i)).getId();
                //mostrarciudad(((Regiones) adapterView.getItemAtPosition(i)).getId());
                mostrarciudad(idregion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idCiudad = ((Ciudades) adapterView.getItemAtPosition(i)).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editNombre.getText().toString().trim().length() == 0 ||
                        editDescripcion.getText().toString().trim().length() == 0 ||
                        editFoto.getText().toString().trim().length() == 0 ||
                        idCiudad == 0)
                    return;

                Atractivos atractivos = new Atractivos();
                atractivos.setIdciudad(idCiudad);
                atractivos.setNombre(editNombre.getText().toString().trim());
                atractivos.setDescripcion(editDescripcion.getText().toString().trim());
                atractivos.setLatitud(editLatitud.getText().toString().trim());
                atractivos.setLongitud(editLogitud.getText().toString().trim());
                atractivos.setFoto(editFoto.getText().toString().trim());

                BackendAtractivos backendAtractivos = Conectar.Backend().create(BackendAtractivos.class);
                backendAtractivos.crear(atractivos).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            editNombre.setText("");
                            editDescripcion.setText("");
                            editLatitud.setText("");
                            editLogitud.setText("");
                            editFoto.setText("");
                            editNombre.requestFocus();

                            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                            builder.setMessage(response.body().toString());
                            builder.create().show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                        builder.setMessage(t.getMessage());
                        builder.create().show();
                    }
                });
            }
        });

        BackendRegiones backendRegiones = Conectar.Backend().create(BackendRegiones.class);
        backendRegiones.regiones().enqueue(new Callback<List<Regiones>>() {
            @Override
            public void onResponse(Call<List<Regiones>> call, Response<List<Regiones>> response) {
                if (response.isSuccessful()) {
                    spinnerRegion.setAdapter(
                            new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, response.body())
                    );
                }
            }

            @Override
            public void onFailure(Call<List<Regiones>> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void mostrarciudad(int idregion) {
        Ciudades ciudades = new Ciudades();
        ciudades.setIdregion(idregion);

        BackendCiudades backendCiudades = Conectar.Backend().create(BackendCiudades.class);
        backendCiudades.buscarporregion(ciudades).enqueue(new Callback<List<Ciudades>>() {
            @Override
            public void onResponse(Call<List<Ciudades>> call, Response<List<Ciudades>> response) {
                if (response.isSuccessful())
                    spinnerCiudad.setAdapter(
                            new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_dropdown_item, response.body())
                    );
            }

            @Override
            public void onFailure(Call<List<Ciudades>> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setMessage("ERROR: " + t.getMessage());
                builder.create().show();
            }
        });
    }

}
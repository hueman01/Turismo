package com.castle.turismo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegionesFragment extends Fragment {
    private RecyclerView recyclerView;
    private Button button;

    public RegionesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_regiones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        button = view.findViewById(R.id.buttonAgregar);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_regioenes_to_nuevaRegionFragment);
            }
        });

        BackendRegiones backendRegiones = Conectar.Backend().create(BackendRegiones.class);
        backendRegiones.regiones().enqueue(new Callback<List<Regiones>>() {
            @Override
            public void onResponse(Call<List<Regiones>> call, Response<List<Regiones>> response) {
                if (response.isSuccessful())
                    recyclerView.setAdapter(new AdaptadorRegion(response.body()));
            }

            @Override
            public void onFailure(Call<List<Regiones>> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
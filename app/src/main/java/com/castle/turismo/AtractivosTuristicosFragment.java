package com.castle.turismo;

import android.content.Intent;
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

public class AtractivosTuristicosFragment extends Fragment implements AdaptadorAtractivos.IOnClick {
    private RecyclerView recyclerView;
    private Button button,salir;



    public AtractivosTuristicosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_atractivos_turisticos, container, false);


    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        button = view.findViewById(R.id.buttonAgregar);
        salir=view.findViewById(R.id.salir);


        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


       salir.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Navigation.findNavController(v).navigate(androidx.constraintlayout.widget.R.id.dragEnd);
           }
       });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_atractivos_to_nuevoAtractivoFragment);
            }
        });

        BackendAtractivos backendAtractivos = Conectar.Backend().create(BackendAtractivos.class);
        backendAtractivos.atractivos().enqueue(new Callback<List<Atractivos>>() {
            @Override
            public void onResponse(Call<List<Atractivos>> call, Response<List<Atractivos>> response) {
                if (response.isSuccessful())
                    recyclerView.setAdapter(new AdaptadorAtractivos(response.body(), AtractivosTuristicosFragment.this));
            }


            @Override
            public void onFailure(Call<List<Atractivos>> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void iconClick(Atractivos atractivos) {
        Bundle bundle = new Bundle();
        bundle.putString("url", atractivos.getFoto());
        bundle.putString("texto", atractivos.getDescripcion());
        Navigation.findNavController(requireView()).navigate(R.id.action_nav_atractivos_to_fotoFragment, bundle);


    }

}
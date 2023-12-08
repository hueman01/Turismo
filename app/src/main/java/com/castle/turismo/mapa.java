package com.castle.turismo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapa extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {

    EditText txtla,txtlo;
    GoogleMap mmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        txtla=findViewById(R.id.txtla);
        txtlo=findViewById(R.id.txtlo);

        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);






    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mmap=googleMap;
        this.mmap.setOnMapClickListener(this);
        this.mmap.setOnMapLongClickListener(this);

        LatLng chile=new LatLng(-37.4684811,-72.3556984);
        mmap.addMarker(new MarkerOptions().position(chile).title("Los Angeles"));
        mmap.moveCamera(CameraUpdateFactory.newLatLng(chile));
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        txtla.setText(""+latLng.latitude);
        txtlo.setText(""+latLng.longitude);
        mmap.clear();
        LatLng chile=new LatLng(latLng.latitude,latLng.longitude);
        mmap.addMarker(new MarkerOptions().position(chile).title(""));
        mmap.moveCamera(CameraUpdateFactory.newLatLng(chile));

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        txtla.setText(""+latLng.latitude);
        txtlo.setText(""+latLng.longitude);
        mmap.clear();
        LatLng chile=new LatLng(latLng.latitude,latLng.longitude);
        mmap.addMarker(new MarkerOptions().position(chile).title(""));
        mmap.moveCamera(CameraUpdateFactory.newLatLng(chile));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
package com.castle.turismo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.file.attribute.GroupPrincipal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearCuentaActivity extends AppCompatActivity {

    private Button button;
    private EditText editNombre, editApellido, editEmail, editClave;
    private RadioButton radioM, radioF, radioO, radioPublico, radioAdmin;
    private RadioGroup grupoGenero, grupoTipo;
    private String genero = "M";
    private String tipo = "PUBLICO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);

        button = findViewById(R.id.button);
        editNombre = findViewById(R.id.editNombre);
        editApellido = findViewById(R.id.editApellido);
        editEmail = findViewById(R.id.editEmail);
        editClave = findViewById(R.id.editClave);
        radioM = findViewById(R.id.radioM);
        radioF = findViewById(R.id.radioF);
        radioO = findViewById(R.id.radioO);
        radioPublico = findViewById(R.id.radioPublico);
        radioAdmin = findViewById(R.id.radioAdmin);
        grupoGenero = findViewById(R.id.group_genero);
        grupoTipo = findViewById(R.id.groupTipo);

        grupoGenero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioSeleccionado = grupoGenero.getCheckedRadioButtonId();
                if (radioSeleccionado == radioM.getId()) {
                    genero = "M";
                } else if (radioSeleccionado == radioF.getId()) {
                    genero = "F";
                } else {
                    genero = "O";
                }
            }
        });

        grupoTipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioSeleccionado = grupoTipo.getCheckedRadioButtonId();
                if (radioSeleccionado == radioAdmin.getId()) {
                    tipo = "ADMIN";
                } else {
                    tipo = "PUBLICO";
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editNombre.getText().toString().trim().length() == 0 ||
                        editApellido.getText().toString().trim().length() == 0 ||
                        editEmail.getText().toString().trim().length() == 0 ||
                        editClave.getText().toString().trim().length() == 0) {
                    Toast.makeText(CrearCuentaActivity.this, "Debe brindar todos los datos", Toast.LENGTH_LONG).show();
                    return;
                }

                Usuarios usuarios = new Usuarios();
                usuarios.setNombre(editNombre.getText().toString().trim());
                usuarios.setApellido(editApellido.getText().toString().trim());
                usuarios.setEmail(editEmail.getText().toString().trim());
                usuarios.setClave(editClave.getText().toString().trim());
                usuarios.setGenero(genero);
                usuarios.setTipousuario(tipo);

                BackendUsuarios backendUsuarios = Conectar.Backend().create(BackendUsuarios.class);
                backendUsuarios.crear(usuarios).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(CrearCuentaActivity.this);
                            builder.setMessage(response.body().toString());
                            builder.create().show();

                            editNombre.setText("");
                            editApellido.setText("");
                            editEmail.setText("");
                            editClave.setText("");
                            editNombre.requestFocus();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CrearCuentaActivity.this);
                        builder.setMessage("ERROR: " + t.getMessage());
                        builder.create().show();
                    }
                });
            }
        });
    }
}
package com.castle.turismo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IngresarActivity extends AppCompatActivity {

    GoogleSignInOptions sgo;
    GoogleSignInClient gsc;

    private Button button,ubicacion;
    private EditText textCorreo, textPassword;
    private TextView textView, google;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        button = findViewById(R.id.buttonAceder);
        textCorreo = findViewById(R.id.editTextEmail);
        textPassword = findViewById(R.id.editTextClave);
        textView = findViewById(R.id.textCrear);
        ubicacion = findViewById(R.id.ubicacion);
        google=findViewById(R.id.google);

        sgo= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        gsc= GoogleSignIn.getClient(this,sgo);

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();



            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregar(view);
                Usuarios usuarios = new Usuarios();
                usuarios.setEmail(textCorreo.getText().toString());
                usuarios.setClave(textPassword.getText().toString());

                BackendUsuarios backendUsuarios = Conectar.Backend().create(BackendUsuarios.class);
                backendUsuarios.acceder(usuarios).enqueue(new Callback<Usuarios>() {
                    @Override
                    public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                        agregar(view);
                        String c1=textCorreo.getText().toString();
                        String c2=textPassword.getText().toString();
                        if (response.isSuccessful()) {
                            if (c1.isEmpty()){
                                agregar(view);
                            }
                            if (c2.isEmpty()){
                                agregar(view);
                            }
                        }else{
                            startActivity(new Intent(IngresarActivity.this, MainActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuarios> call, Throwable t) {
                        agregar(view);
                        Toast.makeText(IngresarActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent e=new Intent(IngresarActivity.this, mapa.class);
                startActivity(e);



            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IngresarActivity.this, CrearCuentaActivity.class));
            }
        });
    }
    void signIn(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecondActivity();
            } catch (ApiException e) {
                Log.d("error", e.toString());
                Toast.makeText(getApplicationContext(), "ha ocurrido un error", Toast.LENGTH_SHORT).show();
            }
        } }
    void navigateToSecondActivity()
    {
        finish();
        Intent intent = new Intent(IngresarActivity.this, bienvenido.class);
        startActivity(intent);
    }

    public void agregar(View z){

        if(validar()){

            Toast.makeText(this,"Datos Ingresados Correctamente ",Toast.LENGTH_SHORT).show();



        }

    }

    public boolean validar(){

        boolean retorno=true;

        String c1=textCorreo.getText().toString();
        if (c1.isEmpty()) {

            textCorreo.setError("Debe Ingresar una Correo Electronico");
            retorno = false;
        }
        String c2=textPassword.getText().toString();
        if (c2.isEmpty()) {

            textPassword.setError("Debe Ingresar una Contraseña");
            retorno = false;
        }

        return retorno;

    };


}
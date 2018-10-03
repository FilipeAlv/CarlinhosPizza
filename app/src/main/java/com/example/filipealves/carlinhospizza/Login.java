package com.example.filipealves.carlinhospizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText login = findViewById(R.id.edit_txt_login);
        final EditText password = findViewById(R.id.edit_txt_password);
        final ImageButton bnt_login = findViewById(R.id.button_login);

        bnt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarUsuario(login.getText().toString(), password.getText().toString())){
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });


    }

    private boolean validarUsuario(String login, String senha){
        if (login.equals("filipe")){
            if (senha.equals("filipe")){
                return true;
            }else{
                Toast.makeText(getApplicationContext(), "Senha incorreta", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(getApplicationContext(), "Este usuário não existe", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

package com.example.filipealves.carlinhospizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filipealves.carlinhospizza.dao.DAOUsuario;
import com.example.filipealves.carlinhospizza.models.Usuario;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText login = findViewById(R.id.edit_txt_login);
        final EditText password = findViewById(R.id.edit_txt_password);
        final ImageButton bnt_login = findViewById(R.id.button_login);
        final TextView tvCadastrar = findViewById(R.id.tvCadastrar);

        bnt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarUsuario(login.getText().toString(), password.getText().toString())){
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(), "Erro ao logar. Verifique o login e a  senha.", Toast.LENGTH_SHORT).show();
                    login.setText("");
                    password.setText("");
                }
            }
        });

        tvCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, activity_cadastrar.class);
                startActivity(intent);
            }
        });


    }

    private boolean validarUsuario(String login, String senha){
        DAOUsuario  daoUsuario = new DAOUsuario(this);
        Usuario usuario = daoUsuario.select_verificarLogin(login,senha);

            if(usuario != null){
                return true;
            }

            return false;

        }
}

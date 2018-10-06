package com.example.filipealves.carlinhospizza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filipealves.carlinhospizza.dao.DAOUsuario;
import com.example.filipealves.carlinhospizza.models.Usuario;

public class Login extends AppCompatActivity {
    DAOUsuario daoUsuario = DAOUsuario.getInstance(this);
    static EditText login;
    static EditText password;
    static boolean logado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        final EditText login = findViewById(R.id.edit_txt_login);
        final EditText password = findViewById(R.id.edit_txt_password);
        final Button bnt_login = findViewById(R.id.button_login);
        final Button btnCadastrar = findViewById(R.id.btn_Cadastrar);


        bnt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarUsuario(login.getText().toString(), password.getText().toString())){


                    Usuario usuario = new Usuario(login.getText().toString(), password.getText().toString());

                    daoUsuario.deleteAll();
                    daoUsuario.insert(usuario);
                    Log.d("", daoUsuario.select().get(0).toString());

                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                    logado = true;
                }else{
                    Toast.makeText(getApplicationContext(), "Erro ao logar. Verifique o login e a  senha.", Toast.LENGTH_SHORT).show();
                    login.setText("");
                    password.setText("");
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, activity_cadastrar.class);
                startActivity(intent);
            }
        });


    }

    private boolean validarUsuario(String login, String senha) {
        //VAI PEGAR DO SERVIDOR
//        DAOUsuario  daoUsuario = new DAOUsuario(this);
//        Usuario usuario = daoUsuario.select_verificarLogin(login,senha);
//
//            if(usuario != null){
//                return true;
//            }
//
//            return false;
//
//        }
        return true;
    }
}

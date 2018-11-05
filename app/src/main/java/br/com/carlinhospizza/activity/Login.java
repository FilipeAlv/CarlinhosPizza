package br.com.carlinhospizza.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filipealves.carlinhospizza.R;

import br.com.carlinhospizza.dao.DAOUsuario;
import br.com.carlinhospizza.models.ClienteRet;
import br.com.carlinhospizza.models.Usuario;
import br.com.carlinhospizza.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    DAOUsuario daoUsuario = DAOUsuario.getInstance(getBaseContext());
    static EditText login;
    static EditText password;
    static TextView tv_esqueceu_senha;
    static boolean logado;
    EditText edPassword;
    ClienteRet usuario;
    EditText edLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        edLogin  = findViewById(R.id.edit_txt_login);
        edPassword = findViewById(R.id.edit_txt_password);
        final Button bnt_login = findViewById(R.id.button_login);
        final Button btnCadastrar = findViewById(R.id.btn_Cadastrar);

        tv_esqueceu_senha = (TextView)findViewById(R.id.txt_esqueceu_senha);

        tv_esqueceu_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ActivityEqueceuSenha.class);
                startActivity(intent);
            }
        });



        bnt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUsuario(edLogin.getText().toString(), edPassword.getText().toString());

            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ActivityCadastro.class);
                startActivity(intent);
            }
        });


    }

    private void validarUsuario(final String login, final String senha) {
        Call<ClienteRet> call = new RetrofitConfig().getClienteService().validarCliente(login, senha);
        call.enqueue(new Callback<ClienteRet>() {
            @Override
            public void onResponse(Call<ClienteRet> call, Response<ClienteRet> response) {
                usuario = response.body();
                if(usuario.getLogin()!=null) {
                    mudarActivity(usuario);
                }else {
                    Toast.makeText(getApplicationContext(), "Erro ao logar. Verifique o login e a  senha.", Toast.LENGTH_SHORT).show();
                    edLogin.setText("");
                    edPassword.setText("");
                }
            }

            @Override
            public void onFailure(Call<ClienteRet> call, Throwable t) {

            }
        });

    }

    private void mudarActivity(ClienteRet cliente){
        Usuario usuario = new Usuario(cliente.getLogin(), cliente.getSenha());

        daoUsuario.deleteAll();
        daoUsuario.insert(usuario);
        Log.d("", daoUsuario.select().get(0).toString());

        Intent i = new Intent(Login.this, MainActivity.class);
        startActivity(i);
        logado = true;

    }
}

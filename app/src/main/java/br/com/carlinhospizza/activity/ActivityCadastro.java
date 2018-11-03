package br.com.carlinhospizza.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.filipealves.carlinhospizza.R;

import br.com.carlinhospizza.fragments.CadastroDadosUsuarioFragment;
import br.com.carlinhospizza.models.Cliente;

public class ActivityCadastro extends AppCompatActivity {
    public static Cliente CLIENTE;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        if(savedInstanceState == null){
            CLIENTE = new Cliente("","");
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout_cadastro, new CadastroDadosUsuarioFragment()).commit();
        }


    }
}

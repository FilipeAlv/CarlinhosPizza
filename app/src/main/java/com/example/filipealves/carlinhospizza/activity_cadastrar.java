package com.example.filipealves.carlinhospizza;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class activity_cadastrar extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout_cadastro, new fragment_dados_usuario()).commit();
        }


    }
}

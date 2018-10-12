package com.example.filipealves.carlinhospizza;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

public class activity_esqueci_Minha_Senha extends AppCompatActivity {
    public static final int PERMISSAO_ENVIAR_MSG = 1;
    Button btnEnviarCod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esqueci_minha_senha);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btnEnviarCod = (Button)findViewById(R.id.btn_enviarCodigoEsqueciMinhaSenha);

        btnEnviarCod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enviarMSG();

            }
        });


    }

    public void enviarMSG() {


//        for(int i = 0; i > 4; i ++){
//            cod += String.valueOf(rand.nextInt(100));
//        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) !=
                PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.SEND_SMS)) {

            } else {
                Log.d("", "Entrou 03");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, PERMISSAO_ENVIAR_MSG);
            }
        } else {
            Log.d("", "Entrou 04");
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage("087988030522", null, "Teste", null, null);
            Toast.makeText(this, "SMS ENVIADO", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,  int[] grantResults) {
        switch (requestCode) {
            case PERMISSAO_ENVIAR_MSG: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("teste", null, "teste", null, null);
                    Toast.makeText(this, "SMS ENVIADO", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "FALHA AO ENVIAR SMS", Toast.LENGTH_LONG).show();
                    return;
                }

            }


        }
    }

}

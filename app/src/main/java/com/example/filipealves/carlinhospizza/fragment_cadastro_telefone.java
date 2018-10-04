package com.example.filipealves.carlinhospizza;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;




public class fragment_cadastro_telefone extends Fragment {

    Button btnProximoCodigoDeConfirmacao;
    Button btnAnteriorEndereco;
    static EditText edNumeroTelefone;

    public fragment_cadastro_telefone() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_telefone, container, false);
        btnAnteriorEndereco = (Button)view.findViewById(R.id.btn_antetiorEndereco);

        btnProximoCodigoDeConfirmacao = (Button)view.findViewById(R.id.btn_proximoConfirmarCodigo);

        edNumeroTelefone = (EditText)view.findViewById(R.id.edTelefone);

        btnAnteriorEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_cadastro, new fragment_cadastro_endereco()).commit();
            }
        });
        btnProximoCodigoDeConfirmacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_cadastro, new fragment_codigoDeConfirmacao()).commit();
            }
        });
        return view;
    }


}

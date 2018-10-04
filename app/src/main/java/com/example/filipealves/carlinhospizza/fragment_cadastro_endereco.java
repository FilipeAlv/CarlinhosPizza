package com.example.filipealves.carlinhospizza;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class fragment_cadastro_endereco extends Fragment {
    Button btnAnteriorDadosUsuario;
    Button btnProximoTelefone;

    static EditText edNomeRua, edBairro, edCidade, edNumero, edPontoDeReferencia, edLogradouro, edCep;

    public fragment_cadastro_endereco() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_endereco, container, false);

        edNomeRua = (EditText)view.findViewById(R.id.edNomeRua);
        edBairro = (EditText)view.findViewById(R.id.edBairro);
        edCidade = (EditText)view.findViewById(R.id.edCidade);
        edCep = (EditText)view.findViewById(R.id.edCep);
        edLogradouro = (EditText)view.findViewById(R.id.edLograduouro);
        edNumero = (EditText)view.findViewById(R.id.edNumeroCasa);
        edPontoDeReferencia = (EditText)view.findViewById(R.id.edPontoDeReferencia);

        btnAnteriorDadosUsuario =  (Button)view.findViewById(R.id.btn_anteriorDadosUsuario);
        btnProximoTelefone =  (Button)view.findViewById(R.id.btn_proximoTelefone);


        btnAnteriorDadosUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_cadastro, new fragment_dados_usuario()).commit();
            }
        });

        btnProximoTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_cadastro, new fragment_cadastro_telefone()).commit();
            }
        });




        return view;
    }

}

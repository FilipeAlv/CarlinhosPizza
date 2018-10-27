package br.com.carlinhospizza.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filipealves.carlinhospizza.R;


public class fragment_codigoDeConfirmacao extends Fragment {

    Button btnProximoLogin;
    Button btnAnteriorTelefone;
    static EditText edCodigoDeConfirmacao;
    public fragment_codigoDeConfirmacao() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_codigo_de_confirmacao, container, false);

        btnProximoLogin = (Button) view.findViewById(R.id.btn_proximoLogin);
        btnAnteriorTelefone = (Button) view.findViewById(R.id.btn_antetiorTelefone);
        edCodigoDeConfirmacao = (EditText)view.findViewById(R.id.edCodigoDeConfirmacao);


        btnProximoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(
                        edCodigoDeConfirmacao.getText().toString()==""){
                    Toast.makeText(view.getContext(), "Verifique se todos os dados estão preenchidos", Toast.LENGTH_SHORT).show();
                }else {
                     getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_cadastro, new fragment_cadastro_login_e_senha()).commit();
                }
            }
        });


        btnAnteriorTelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_cadastro, new fragment_cadastro_telefone()).commit();
            }
        });



        return view;
    }
    //
}

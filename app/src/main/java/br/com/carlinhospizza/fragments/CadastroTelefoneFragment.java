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
import br.com.carlinhospizza.activity.ActivityCadastro;

import br.com.carlinhospizza.util.Util;


public class CadastroTelefoneFragment extends Fragment {

    Button btnProximoCodigoDeConfirmacao;
    Button btnAnteriorEndereco;
    static EditText etNumeroTelefone;

    public CadastroTelefoneFragment() {
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

        etNumeroTelefone = (EditText)view.findViewById(R.id.edTelefone);


        Util.mascararEditText("(NN)NNNNN-NNNN", etNumeroTelefone);



        btnAnteriorEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_cadastro, new CadastroEnderecoFragment()).commit();
            }
        });
        btnProximoCodigoDeConfirmacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNumeroTelefone.getText().length()==0){
                    Toast.makeText(view.getContext(), "Seu número de telefone é importante para o cadastro.", Toast.LENGTH_SHORT).show();
                }else {
                    ActivityCadastro.CLIENTE.setTelefone(etNumeroTelefone.getText().toString());
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_cadastro, new CadastroLoginSenhaFragment()).commit();
                }

            }
        });
        return view;
    }


}

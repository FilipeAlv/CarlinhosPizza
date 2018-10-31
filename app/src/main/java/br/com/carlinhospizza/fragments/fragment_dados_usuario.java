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
import br.com.carlinhospizza.CadastroActivity;

import br.com.carlinhospizza.Util.Util;


public class fragment_dados_usuario extends Fragment {
    Button btnProximoEndereco;
    static EditText edNome, edCPF, edRG, edData_nascimento;
    public fragment_dados_usuario() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_cadastro_dados_usuario, container, false);

        btnProximoEndereco = (Button)view.findViewById(R.id.btn_ProximoEndereco);
        edNome = (EditText)view.findViewById(R.id.edNome);
        edCPF = (EditText)view.findViewById(R.id.edCPF);
        edRG= (EditText)view.findViewById(R.id.edRG);
        edData_nascimento = (EditText)view.findViewById(R.id.edData_nascimento);


        Util.mascararEditText("NNN.NNN.NNN-NN", edCPF);
        Util.mascararEditText("NN/NN/NNNN", edData_nascimento);


        btnProximoEndereco.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(
                       edNome.getText().length()==0||
                       edCPF.getText().length()==0||
                       edRG.getText().length()==0||
                       edData_nascimento.getText().length()==0 || !Util.isCPF(edCPF.getText().toString())){
                   Toast.makeText(view.getContext(), "Verifique se todos os dados estão preenchidos " +
                           "corretamente. Certifique-se que o seu CPF é válido.", Toast.LENGTH_LONG).show();
               }else {
                   CadastroActivity.CLIENTE.setNome(edNome.getText().toString());
                   CadastroActivity.CLIENTE.setCpf(edCPF.getText().toString());
                   CadastroActivity.CLIENTE.setRg(edRG.getText().toString());
                   CadastroActivity.CLIENTE.setData_nascimento(edData_nascimento.getText().toString());
                   acaoProximoEndereco();
               }
           }
       });

        return view;


    }

    public void acaoProximoEndereco(){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_cadastro, new fragment_cadastro_endereco()).commit();
    }








}

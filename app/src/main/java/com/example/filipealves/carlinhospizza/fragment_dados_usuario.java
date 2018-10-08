package com.example.filipealves.carlinhospizza;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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


        btnProximoEndereco.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if(
                       edNome.getText().length()==0||
                       edCPF.getText().length()==0||
                       edRG.getText().length()==0||
                       edData_nascimento.getText().length()==0){
                   Toast.makeText(view.getContext(), "Verifique se todos os dados estão preenchidos corretamente", Toast.LENGTH_SHORT).show();
               }else {
                   activity_cadastrar.CLIENTE.setNome(edNome.getText().toString());
                   activity_cadastrar.CLIENTE.setCpf(edCPF.getText().toString());
                   activity_cadastrar.CLIENTE.setRg(edRG.getText().toString());
                   activity_cadastrar.CLIENTE.setData_nascimento(edData_nascimento.getText().toString());
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

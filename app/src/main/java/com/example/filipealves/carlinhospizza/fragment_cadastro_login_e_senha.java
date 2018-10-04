package com.example.filipealves.carlinhospizza;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.filipealves.carlinhospizza.dao.DAOUsuario;
import com.example.filipealves.carlinhospizza.models.Usuario;


public class fragment_cadastro_login_e_senha extends Fragment {
    Button btnCadastrar;
    Button btnAnteriorConfirmarCodigo;
    static EditText edLogin, edSenha;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastro_login_e_senha, container, false);

        btnAnteriorConfirmarCodigo = (Button)view.findViewById(R.id.btn_anteriorConfirmarCodigo);
        btnCadastrar = (Button)view.findViewById(R.id.btn_cadastrar);

        edLogin = (EditText)view.findViewById(R.id.edLogin);
        edSenha = (EditText) view.findViewById(R.id.edSenha);

        final DAOUsuario daoUsuario = DAOUsuario.getInstance(getContext());

        btnAnteriorConfirmarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_cadastro, new fragment_codigoDeConfirmacao()).commit();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FAZER O QUE TEM QUE FAZER E LIMPAR OS CAMPOS

                Usuario usuario = new Usuario(fragment_cadastro_login_e_senha.edLogin.getText().toString(),
                        fragment_cadastro_login_e_senha.edSenha.getText().toString());

                daoUsuario.deleteAll();
                daoUsuario.insert(usuario);

                Log.d("", daoUsuario.select().get(0).toString());

                //
                fragment_cadastro_login_e_senha.edLogin.setText("");
                fragment_cadastro_login_e_senha.edSenha.setText("");



                Toast.makeText(getContext(), "Usuário cadastrado com sucesso.", Toast.LENGTH_SHORT).show();

                getActivity().finish();

            }
        });

        return view;
    }



}

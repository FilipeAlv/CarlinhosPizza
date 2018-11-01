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
import br.com.carlinhospizza.dao.DAOUsuario;
import br.com.carlinhospizza.models.Cliente;
import br.com.carlinhospizza.models.ClienteRet;
import br.com.carlinhospizza.retrofit.RetrofitConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_cadastro_login_e_senha extends Fragment {
    Button btnCadastrar;
    Button btnAnteriorConfirmarCodigo;
    static EditText edLogin, edSenha, edConfSenha;

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
        edConfSenha = view.findViewById(R.id.edConfirmarSenha);

        final DAOUsuario daoUsuario = DAOUsuario.getInstance(getContext());

        btnAnteriorConfirmarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_cadastro, new fragment_cadastro_telefone()).commit();
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(
                        edLogin.getText().length()==0||
                        edSenha.getText().length()==0||
                        edConfSenha.getText().length()==0){
                    Toast.makeText(view.getContext(), "Verifique se todos os dados estão preenchidos corretamente", Toast.LENGTH_SHORT).show();
                }else if (!edConfSenha.getText().toString().trim().equals(edSenha.getText().toString().trim())){
                    Toast.makeText(view.getContext(), "As senhas inseridas não são iguais", Toast.LENGTH_SHORT).show();

                }else{
                    validarExistencia(edLogin.getText().toString().trim());
                }

            }
        });

        return view;
    }

    private void cadastrarCliente(final Cliente cliente){
                        Call<String> call = new RetrofitConfig().getClienteService().insertUser(
                        cliente.getEndereco().getRua(),
                        cliente.getEndereco().getNumero(),
                        cliente.getEndereco().getBairro(),
                        cliente.getEndereco().getCidade(),
                        cliente.getEndereco().getCep(),
                        cliente.getEndereco().getPonto_referencia(),
                        cliente.getNome(),
                        cliente.getCpf(),
                        cliente.getRg(),
                        cliente.getData_nascimento(),
                        cliente.getLogin(),
                        cliente.getPassword(),
                        cliente.getTelefone());
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {

                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });



    }

    private void validarExistencia(final String login){
        Call<ClienteRet> call = new RetrofitConfig().getClienteService().buscarCliente(login);
        call.enqueue(new Callback<ClienteRet>() {
            @Override
            public void onResponse(Call<ClienteRet> call, Response<ClienteRet> response) {
                ClienteRet cliente = response.body();
                if (login.equals(cliente.getLogin())){
                    Toast.makeText(getContext(), "Este usuário já está sendo utilizado.", Toast.LENGTH_SHORT).show();
                }else{
                    CadastroActivity.CLIENTE.setLogin(edLogin.getText().toString());
                    CadastroActivity.CLIENTE.setPassword(edSenha.getText().toString());
                    cadastrarCliente(CadastroActivity.CLIENTE);
                    Toast.makeText(getContext(), "Este usuário já está sendo utilizado.", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            }

            @Override
            public void onFailure(Call<ClienteRet> call, Throwable t) {

            }
        });
    }


}

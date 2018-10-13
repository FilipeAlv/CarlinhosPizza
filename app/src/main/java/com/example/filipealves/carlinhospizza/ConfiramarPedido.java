package com.example.filipealves.carlinhospizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filipealves.carlinhospizza.dao.DAOUsuario;
import com.example.filipealves.carlinhospizza.models.Cliente;
import com.example.filipealves.carlinhospizza.models.Endereco;
import com.example.filipealves.carlinhospizza.models.Pedido;
import com.example.filipealves.carlinhospizza.models.Usuario;
import com.example.filipealves.carlinhospizza.retrofit.RetrofitConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConfiramarPedido extends AppCompatActivity {
    private EditText edNomeRua;
    private EditText edBairro;
    private Spinner edCidade;
    private EditText edCep;
    private EditText edNumero;
    private EditText edPontoDeReferencia;
    private Endereco endereco;
    private Usuario usuario;
    private int cliente_id;
    private Pedido pedidoCadastrado;
    private String formaPagamento="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confiramar_pedido);
        ArrayList<Usuario> usuarios = DAOUsuario.getInstance(getApplicationContext()).select();
        usuario = usuarios.get(0);
        Call<Endereco> call = new RetrofitConfig().getClienteService().buscarEndereco(usuario.getLogin());

        edNomeRua =  findViewById(R.id.edNomeRua);
        edBairro  =  findViewById(R.id.edBairro);
        edCidade  =  findViewById(R.id.edCidade);
        edCep     =  findViewById(R.id.edCep);
        edNumero  =  findViewById(R.id.edNumeroCasa);
        edPontoDeReferencia =  findViewById(R.id.edPontoDeReferencia);

        final RadioButton dinheiro = findViewById(R.id.checkDinheiro);
        final RadioButton cartao = findViewById(R.id.checkCartao);
        final TextView tvTroco = findViewById(R.id.txtTroco);
        final EditText edTroco = findViewById(R.id.edit_troco);

        final EditText edValor = findViewById(R.id.pedidoValorTotal);
        final Button finalizar = findViewById(R.id.btn_FinalizarPedido);

        edValor.setText("R$" + meus_pedidos.valorT + "0");

        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                endereco = response.body();
                edNomeRua.setText(endereco.getRua());
                edBairro.setText(endereco.getBairro());
                edNumero.setText(endereco.getNumero());
                edCidade.setSelection(getPosition(endereco.getCidade()));
                edCep.setText(endereco.getCep());
                edPontoDeReferencia.setText(endereco.getPonto_referencia());
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {

            }
        });

        dinheiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    tvTroco.setVisibility(View.VISIBLE);
                    edTroco.setVisibility(View.VISIBLE);

            }
        });

        cartao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    edTroco.setVisibility(View.INVISIBLE);
                    tvTroco.setVisibility(View.INVISIBLE);

            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"passou aqui",Toast.LENGTH_SHORT).show();
                Call<Cliente> call = new RetrofitConfig().getClienteService().buscarCliente(usuario.getLogin());
                /*call.enqueue(new Callback<Cliente>() {
                    @Override
                    public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                        Cliente cliente = response.body();
                        cliente_id = cliente.getId();

                    }

                    @Override
                    public void onFailure(Call<Cliente> call, Throwable t) {

                    }
                });*/
                Pedido pedido = new Pedido();
                pedido.setDescricao("");
                pedido.setValor(edValor.getText().toString());
                pedido.setForma_pagamento(formaPagamento);
                pedido.setCliente_id(cliente_id);
                pedido.setEndereco_id(endereco.getId());

                cadastrarPedido(pedido);



            }
        });
    }



    private int getPosition(String cidade) {
        String[] cidades = getResources().getStringArray(R.array.cidades_list);
        for (int i = 0; i < cidades.length; i++) {
            if (cidades[i].equals(cidade)) {
                return i;
            }
        }
        return 0;
    }


    private void cadastrarPedido(final Pedido pedido){
        if (
                edNomeRua.getText().equals(endereco.getRua())||
        edBairro.getText().equals(endereco.getBairro())||
        edNumero.getText().equals(endereco.getNumero())||
        edCidade.getSelectedItem().equals(getPosition(endereco.getCidade()))||
        edCep.getText().equals(endereco.getCep())||
        edPontoDeReferencia.getText().equals(endereco.getPonto_referencia())) {



            Call<Pedido> call1 = new RetrofitConfig().getPedidoService().insertPedido(
                    pedido.getDescricao(),
                    pedido.getValor(),
                    pedido.getForma_pagamento(),
                    pedido.getTroco(),
                    pedido.getCliente_id(),
                    pedido.getEndereco_id());
            call1.enqueue(new Callback<Pedido>() {
                @Override
                public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                    pedidoCadastrado = response.body();
                }

                @Override
                public void onFailure(Call<Pedido> call, Throwable t) {

                }
            });
        }

    }
}

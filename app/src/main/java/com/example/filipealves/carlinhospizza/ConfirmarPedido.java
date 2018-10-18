package com.example.filipealves.carlinhospizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.filipealves.carlinhospizza.models.ClienteRet;
import com.example.filipealves.carlinhospizza.models.Endereco;
import com.example.filipealves.carlinhospizza.models.Pedido;
import com.example.filipealves.carlinhospizza.models.PedidoRet;
import com.example.filipealves.carlinhospizza.models.Produto;
import com.example.filipealves.carlinhospizza.models.Usuario;
import com.example.filipealves.carlinhospizza.retrofit.RetrofitConfig;

import java.text.NumberFormat;
import java.util.ArrayList;

import Util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConfirmarPedido extends AppCompatActivity {
    private EditText edNomeRua;
    private EditText edBairro;
    private Spinner edCidade;
    private EditText edCep;
    private EditText edNumero;
    private EditText edPontoDeReferencia;
    private Endereco endereco;
    private Usuario usuario;
    private int cliente_id;
    private PedidoRet pedidoCadastrado = new PedidoRet();
    int pedido_id;
    private String formaPagamento = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confiramar_pedido);
        ArrayList<Usuario> usuarios = DAOUsuario.getInstance(getApplicationContext()).select();
        usuario = usuarios.get(0);
        Call<Endereco> call = new RetrofitConfig().getClienteService().buscarEndereco(usuario.getLogin());

        edNomeRua = findViewById(R.id.edNomeRua);
        edBairro = findViewById(R.id.edBairro);
        edCidade = findViewById(R.id.edCidade);
        edCep = findViewById(R.id.edCep);
        edNumero = findViewById(R.id.edNumeroCasa);
        edPontoDeReferencia = findViewById(R.id.edPontoDeReferencia);

        final RadioButton dinheiro = findViewById(R.id.checkDinheiro);
        final RadioButton cartao = findViewById(R.id.checkCartao);
        final TextView tvTroco = findViewById(R.id.txtTroco);
        final EditText edTroco = findViewById(R.id.edit_troco);
        final EditText edValor = findViewById(R.id.pedidoValorTotal);
        final Button finalizar = findViewById(R.id.btn_FinalizarPedido);

        edTroco.addTextChangedListener(new MascaraMonetaria(edTroco));


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

                Call<ClienteRet> call = new RetrofitConfig().getClienteService().buscarCliente(usuario.getLogin());
                call.enqueue(new Callback<ClienteRet>() {
                    @Override
                    public void onResponse(Call<ClienteRet> call, Response<ClienteRet> response) {
                        ClienteRet cliente = response.body();
                        cliente_id = cliente.getId();
                        Pedido pedido = new Pedido();
                        pedido.setDescricao("");
                        pedido.setValor(edValor.getText().toString());
                        pedido.setForma_pagamento(formaPagamento);
                        pedido.setCliente_id(cliente_id);
                        pedido.setTroco(edTroco.getText().toString());
                        pedido.setEndereco_id(endereco.getId());

                        cadastrarPedido(pedido);
                    }

                    @Override
                    public void onFailure(Call<ClienteRet> call, Throwable t) {

                    }
                });

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


    private void cadastrarPedido(final Pedido pedido) {
        if (
                edNomeRua.getText().toString().equals(endereco.getRua()) &&
                        edBairro.getText().toString().equals(endereco.getBairro()) &&
                        edNumero.getText().toString().equals(endereco.getNumero()) &&
                        edCidade.getSelectedItemId() == getPosition(endereco.getCidade()) &&
                        edCep.getText().toString().equals(endereco.getCep()) &&
                        edPontoDeReferencia.getText().toString().equals(endereco.getPonto_referencia())) {


            Call<PedidoRet> call1 = new RetrofitConfig().getPedidoService().insertPedido(
                    pedido.getDescricao(),
                    pedido.getValor(),
                    pedido.getForma_pagamento(),
                    pedido.getTroco(),
                    pedido.getCliente_id(),
                    pedido.getEndereco_id());

            call1.enqueue(new Callback<PedidoRet>() {
                @Override
                public void onResponse(Call<PedidoRet> call, Response<PedidoRet> response) {
                    pedidoCadastrado = response.body();
                    adicionarProdutos(pedidoCadastrado.getId());

                }

                @Override
                public void onFailure(Call<PedidoRet> call, Throwable t) {

                }
            });
        } else {
            Call<PedidoRet> call1 = new RetrofitConfig().getPedidoService().insertPedidoEndereco(
                    pedido.getDescricao(),
                    pedido.getValor(),
                    pedido.getForma_pagamento(),
                    pedido.getTroco(),
                    pedido.getCliente_id(),
                    edNomeRua.getText().toString(),
                    edNumero.getText().toString(),
                    edBairro.getText().toString(),
                    edCidade.getSelectedItem().toString(),
                    edCep.getText().toString(),
                    edPontoDeReferencia.getText().toString());

            call1.enqueue(new Callback<PedidoRet>() {
                @Override
                public void onResponse(Call<PedidoRet> call, Response<PedidoRet> response) {
                    pedidoCadastrado = response.body();
                    adicionarProdutos(pedidoCadastrado.getId());

                }

                @Override
                public void onFailure(Call<PedidoRet> call, Throwable t) {

                }
            });
        }


    }

    private void adicionarProdutos(int pedido) {

        for (Produto produto : MainActivity.pedido.getProdutos()) {
            Call<PedidoRet> call2 = new RetrofitConfig().getPedidoService().insertPedidoProduto(
                    pedido,
                    produto.getId(),
                    produto.getQuantidade(),
                    "");

            call2.enqueue(new Callback<PedidoRet>() {
                @Override
                public void onResponse(Call<PedidoRet> call, Response<PedidoRet> response) {
                    PedidoRet pedidoR = response.body();
                }

                @Override
                public void onFailure(Call<PedidoRet> call, Throwable t) {

                }
            });
        }
    }

    private class MascaraMonetaria implements TextWatcher {


        final EditText campo;


        public MascaraMonetaria(EditText campo) {

            super();

            this.campo = campo;

        }


        private boolean isUpdating = false;

        // Pega a formatacao do sistema, se for brasil R$ se EUA US$

        private NumberFormat nf = NumberFormat.getCurrencyInstance();


        @Override

        public void onTextChanged(CharSequence s, int start, int before,

                                  int after) {

            // Evita que o método seja executado varias vezes.

            // Se tirar ele entre em loop

            if (isUpdating) {

                isUpdating = false;

                return;
            }


            isUpdating = true;

            String str = s.toString();

            // Verifica se já existe a máscara no texto.

            boolean hasMask = ((str.indexOf("R$") > -1 || str.indexOf("$") > -1) &&

                    (str.indexOf(".") > -1 || str.indexOf(",") > -1));

            // Verificamos se existe máscara

            if (hasMask) {

                // Retiramos a máscara.

                str = str.replaceAll("[R$]", "").replaceAll("[,]", "")

                        .replaceAll("[.]", "");
            }


            try {

                // Transformamos o número que está escrito no EditText em

                // monetário.

                str = nf.format(Double.parseDouble(str) / 100);

                campo.setText(str);

                campo.setSelection(campo.getText().length());

            } catch (NumberFormatException e) {

                s = "";

            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }


        @Override

        public void beforeTextChanged(CharSequence s, int start, int count,

                                      int after) {


        }


    }

}
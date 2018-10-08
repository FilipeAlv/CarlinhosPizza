package com.example.filipealves.carlinhospizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filipealves.carlinhospizza.dao.DAOUsuario;
import com.example.filipealves.carlinhospizza.models.Cliente;
import com.example.filipealves.carlinhospizza.models.Endereco;
import com.example.filipealves.carlinhospizza.models.Usuario;
import com.example.filipealves.carlinhospizza.retrofit.RetrofitConfig;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ConfiramarPedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confiramar_pedido);
        ArrayList<Usuario> usuarios = DAOUsuario.getInstance(getApplicationContext()).select();
        Usuario usuario = usuarios.get(0);
        Call<Endereco> call = new RetrofitConfig().getClienteService().buscarEndereco(usuario.getLogin());


        final RadioButton dinheiro = findViewById(R.id.checkDinheiro);
        final RadioButton cartao = findViewById(R.id.checkCartao);
        final TextView tvTroco = findViewById(R.id.txtTroco);
        final EditText edTroco = findViewById(R.id.edit_troco);
        final EditText edNomeRua = (EditText) findViewById(R.id.edNomeRua);
        final EditText edBairro = (EditText) findViewById(R.id.edBairro);
        final Spinner edCidade = findViewById(R.id.edCidade);
        final EditText edCep = (EditText) findViewById(R.id.edCep);
        EditText edLogradouro = (EditText) findViewById(R.id.edLograduouro);
        final EditText edNumero = (EditText) findViewById(R.id.edNumeroCasa);
        final EditText edPontoDeReferencia = (EditText) findViewById(R.id.edPontoDeReferencia);
        final EditText edValor = findViewById(R.id.pedidoValorTotal);

        edValor.setText("R$" + meus_pedidos.valorT + "0");

        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                Endereco endereco = response.body();
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
}

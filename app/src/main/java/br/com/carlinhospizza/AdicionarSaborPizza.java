package br.com.carlinhospizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.filipealves.carlinhospizza.R;

import br.com.carlinhospizza.adapter.ListAdapterAdicionarSabor;
import br.com.carlinhospizza.models.Produto;
import br.com.carlinhospizza.util.Util;

import java.util.ArrayList;

public class AdicionarSaborPizza extends AppCompatActivity {

    private double valorT;
    private TextView valorTotal;
    private String produtoNome;
    private String tamanho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        produtoNome = bundle.getString("produto");
        tamanho = bundle.getString("tamanho");
        valorT = bundle.getDouble("valorT");
        valorTotal = findViewById(R.id.listValorTotal);
        Produto produto = new Produto();
        ArrayList<Produto> sabores = new ArrayList<>();
        for (Produto pizza : Util.PRODUTOS) {
            if (pizza.getNome().equals(produtoNome)){
                produto=pizza;
            }else{
                if(pizza.getTamanho().equals(tamanho)) {
                    sabores.add(pizza);
                }
            }

        }

        setContentView(R.layout.activity_adicionar_sabor_pizza);
        ArrayAdapter<Produto> produtoAdapter = new ListAdapterAdicionarSabor(this,sabores, produto, valorT, valorTotal);
        ListView lvPedidos = (ListView) findViewById(R.id.lv_sabores);
        lvPedidos.setAdapter(produtoAdapter);


    }
}

package br.com.carlinhospizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.filipealves.carlinhospizza.R;

import br.com.carlinhospizza.adapter.listAdapterAdicionarSabor;
import br.com.carlinhospizza.models.Produto;

import java.util.ArrayList;

public class AdicionarSaborPizza extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        String produtoNome = bundle.getString("produto");
        String tamanho = bundle.getString("tamanho");
        Produto produto = new Produto();
        ArrayList<Produto> sabores = new ArrayList<>();
        for (Produto pizza : Splash.PRODUTOS) {
            if (pizza.getNome().equals(produtoNome)){
                produto=pizza;
            }else{
                if(pizza.getTamanho().equals(tamanho)) {
                    sabores.add(pizza);
                }
            }

        }

        setContentView(R.layout.activity_adicionar_sabor_pizza);
        ArrayAdapter<Produto> produtoAdapter = new listAdapterAdicionarSabor(this,sabores, produto);
        ListView lvPedidos = (ListView) findViewById(R.id.lv_sabores);
        lvPedidos.setAdapter(produtoAdapter);


    }
}

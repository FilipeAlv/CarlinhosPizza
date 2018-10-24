package com.example.filipealves.carlinhospizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filipealves.carlinhospizza.adapter.RecycleViewAdapter;
import com.example.filipealves.carlinhospizza.models.Produto;
import com.example.filipealves.carlinhospizza.adapter.listAdapter;

import java.util.ArrayList;


public class meus_pedidos extends AppCompatActivity {
    public static double valorT=0;
    Button confirmarPedido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_pedidos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        confirmarPedido = (Button) findViewById(R.id.bnt_confirmarPedido);
        confirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(meus_pedidos.this, ConfirmarPedido.class);
                startActivity(intent);
            }
        });

        if (MainActivity.pedido.getProdutos().size()==0){
            Toast.makeText(this, "Voce não possui pedidos no momento", Toast.LENGTH_SHORT).show();
        }else {

            for (Produto produto: MainActivity.pedido.getProdutos()) {
                valorT+=Double.parseDouble(produto.getValor());
                produto.setQuantidade(1);
            }

            TextView valorTotal = findViewById(R.id.listValorTotal);
            ArrayAdapter<Produto> pedidosAdapter = new listAdapter(this, MainActivity.pedido.getProdutos(), valorT, valorTotal);
            ListView lvPedidos = (ListView) findViewById(R.id.lv_pedidos);
            lvPedidos.setAdapter(pedidosAdapter);



            valorTotal.setText("R$"+valorT+"0");
        }
    }

    @Override
    protected void onStop() {
        valorT = 0;
        for(RecycleViewAdapter.MyViewHolder holder: RecycleViewAdapter.HOLDERS){
            if (holder.fab.getTag().equals("selecionado")){
                holder.fab.setImageResource(R.drawable.fab_disponivel_24dp);
                holder.fab.setTag("disponivel");
            }
        }
        super.onStop();
    }
}

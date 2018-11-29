package br.com.carlinhospizza.activity;

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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.carlinhospizza.R;
import br.com.carlinhospizza.adapter.MyListAdapter;
import br.com.carlinhospizza.models.Pedido;
import br.com.carlinhospizza.models.Produto;


public class MeusPedidos extends AppCompatActivity {
    private double valorT=0;
    private TextView valorTotal;
    private Button confirmarPedido;
    private Pedido pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_pedidos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pedido = (Pedido) getIntent().getSerializableExtra("pedido");
        confirmarPedido = (Button) findViewById(R.id.bnt_confirmarPedido);
        confirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pedido.getProdutos().size()==0){
                    Toast.makeText(getApplicationContext(),"Voce não possui pedidos no momento", Toast.LENGTH_SHORT).show();
                }else if(validarDia()){
                    Toast.makeText(getApplicationContext(),"Desculpe! Estamos fechado para descanço. Abriremos amanhã.", Toast.LENGTH_LONG).show();
                }else if(!validarHorario()){
                    Toast.makeText(getApplicationContext(),R.string.fora_do_horario, Toast.LENGTH_LONG).show();
                }else{
                    Intent intent = new Intent(MeusPedidos.this, ConfirmarPedido.class);
                    startActivity(intent);
                }
            }
        });

        if (pedido.getProdutos().size()==0){
            Toast.makeText(this, "Voce não possui pedidos no momento", Toast.LENGTH_SHORT).show();
        }else {

            somarProdutos();

            valorTotal = findViewById(R.id.listValorTotal);
            ArrayAdapter<Produto> pedidosAdapter = new MyListAdapter(this, pedido.getProdutos(), valorT, valorTotal);
            ListView lvPedidos = (ListView) findViewById(R.id.lv_pedidos);
            lvPedidos.setAdapter(pedidosAdapter);
            valorTotal.setText("R$"+valorT+"0");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private boolean validarHorario(){
        Calendar agora, aberto, fechado;
        agora = new GregorianCalendar();
        aberto = new GregorianCalendar();
        fechado = new GregorianCalendar();

        agora.setTime(new Date());
        aberto.set(Calendar.HOUR_OF_DAY, 18);
        aberto.set(Calendar.MINUTE, 0);
        aberto.set(Calendar.SECOND, 0);
        aberto.set(Calendar.MILLISECOND, 0);

        fechado.set(Calendar.HOUR_OF_DAY, 23);
        fechado.set(Calendar.MINUTE, 30);
        fechado.set(Calendar.SECOND, 0);
        fechado.set(Calendar.MILLISECOND, 0);

        if(agora.getTime().after(aberto.getTime()) && agora.getTime().before(fechado.getTime())){
            return true;
        }

        return false;
    }


    private boolean validarDia(){
        Calendar hoje = new GregorianCalendar();

        if (hoje.DAY_OF_WEEK==Calendar.MONDAY)
            return  true;
        return false;
    }

    public void somarProdutos(){
        for (Produto produto: pedido.getProdutos()) {
            valorT+=Double.parseDouble(produto.getValor());
            produto.setQuantidade(1);
        }
    }

}

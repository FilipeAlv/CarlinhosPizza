package br.com.carlinhospizza;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filipealves.carlinhospizza.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

import br.com.carlinhospizza.models.Pedido;
import br.com.carlinhospizza.models.Produto;
import br.com.carlinhospizza.adapter.listAdapter;


public class meus_pedidos extends AppCompatActivity {
    public static double valorT=0;
    public static  TextView valorTotal;
    Button confirmarPedido;
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
                }else if(!validarHorario()){
                    Toast.makeText(getApplicationContext(),"Desculpe! O horário de pedidos é de 18:00 às 23:30.", Toast.LENGTH_LONG).show();
                }else if(validarDia()){
                    Toast.makeText(getApplicationContext(),"Desculpe! Estamos fechado para descanço. Abriremos amanahã.", Toast.LENGTH_LONG).show();

                }else{
                    Intent intent = new Intent(meus_pedidos.this, ConfirmarPedido.class);
                    intent.putExtra("pedido", pedido);
                    startActivity(intent);
                }
            }
        });

        if (pedido.getProdutos().size()==0){
            Toast.makeText(this, "Voce não possui pedidos no momento", Toast.LENGTH_SHORT).show();
        }else {

            somarProdutos();

            valorTotal = findViewById(R.id.listValorTotal);
            ArrayAdapter<Produto> pedidosAdapter = new listAdapter(this, pedido.getProdutos(), valorT, valorTotal, pedido);
            ListView lvPedidos = (ListView) findViewById(R.id.lv_pedidos);
            lvPedidos.setAdapter(pedidosAdapter);



            valorTotal.setText("R$"+valorT+"0");
        }
    }

    @Override
    protected void onStop() {
        valorT = 0;
        super.onStop();
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
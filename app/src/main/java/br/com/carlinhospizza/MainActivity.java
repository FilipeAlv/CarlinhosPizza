package br.com.carlinhospizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.filipealves.carlinhospizza.R;

import br.com.carlinhospizza.Util.Util;
import br.com.carlinhospizza.adapter.PageAdapterPrincipal;
import br.com.carlinhospizza.adapter.RecycleViewAdapter;
import br.com.carlinhospizza.controller.Controller;
import br.com.carlinhospizza.dao.DAOUsuario;
import br.com.carlinhospizza.models.Pedido;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static ImageButton fabPedidos;
    private  Pedido pedido = new Pedido();
    private  DAOUsuario daoUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        daoUsuario = DAOUsuario.getInstance(this);

        fabPedidos = findViewById(R.id.fabPedidos);
        fabPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.print("CLICK");
                Intent intent = new Intent(MainActivity.this, meus_pedidos.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("pedido", pedido);
                startActivity(intent);
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabLayoutPrincipal);
        viewPager = (ViewPager) findViewById(R.id.viewPagerPrincipal);

        viewPager.setAdapter(new PageAdapterPrincipal(
                getSupportFragmentManager(),
                getResources().getStringArray(R.array.titles_Principal),
                getResources().getStringArray(R.array.titles_Secundaria_tab_1),
                getResources().getStringArray(R.array.titles_Secundaria_tab_2),
                pedido));

        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Util.ZERAR){
            for ( RecycleViewAdapter.MyViewHolder holder : RecycleViewAdapter.HOLDERS) {
                 if (holder.fab.getTag().equals("selecionado")){
                    holder.fab.setImageResource(R.drawable.fab_disponivel_24dp);
                    holder.fab.setTag("disponivel");


                }
            }
        }
        Util.ZERAR=false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_logOf){
            if(Login.logado == true) {
                daoUsuario.deleteAll();

                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                finish();
                Login.logado = false;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public static ImageButton getFavProdutos(){
        return fabPedidos;
    }
}

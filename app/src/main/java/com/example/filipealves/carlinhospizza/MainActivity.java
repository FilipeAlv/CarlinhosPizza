package com.example.filipealves.carlinhospizza;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import com.example.filipealves.carlinhospizza.adapter.PageAdapterPrincipal;
import com.example.filipealves.carlinhospizza.dao.DAOUsuario;
import com.example.filipealves.carlinhospizza.models.Pedido;
import com.example.filipealves.carlinhospizza.models.Usuario;


public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static ImageButton fabPedidos;
    public static Pedido pedido = new Pedido();
    public  DAOUsuario daoUsuario = DAOUsuario.getInstance(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fabPedidos = findViewById(R.id.fabPedidos);
        fabPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, meus_pedidos.class);
                startActivity(intent);
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabLayoutPrincipal);
        viewPager = (ViewPager) findViewById(R.id.viewPagerPrincipal);

        viewPager.setAdapter(new PageAdapterPrincipal(getSupportFragmentManager(), getResources().getStringArray(R.array.titles_Principal),
                getResources().getStringArray(R.array.titles_Secundaria_tab_1),
                getResources().getStringArray(R.array.titles_Secundaria_tab_2)));

        tabLayout.setupWithViewPager(viewPager);





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

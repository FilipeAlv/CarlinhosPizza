package br.com.carlinhospizza.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.widget.HorizontalScrollView;
import br.com.carlinhospizza.fragments.FragmentProdutos;
import br.com.carlinhospizza.models.Pedido;

public class PageAdapterSecundaria extends FragmentStatePagerAdapter {
    String[] titles;
    private HorizontalScrollView horizontalScrollView;
    private FragmentProdutos fragmentProdutos;
    private Pedido pedido;
    private String tipo;
    public PageAdapterSecundaria(FragmentManager fm, String[] titles, String tipo, Pedido pedido) {
        super(fm);
        this.titles =titles;
        this.tipo = tipo;
        this.pedido = pedido;
    }

    @Override
    public Fragment getItem(final int position) {
        //horizontalScrollView=PageAdapterPrincipal.getFragmentPrincipal().getHorizontalScrollView();

        // if (position%4 == 0) {
        //     horizontalScrollView.post(new Runnable() {
        //         public void run() {
        //             horizontalScrollView.smoothScrollTo(position * 120, 120);
        //         }
        //     });
        // }
        Bundle bundle = new Bundle();
        fragmentProdutos = new FragmentProdutos();
        bundle.putString("title", titles[position]);
        bundle.putSerializable("pedido", pedido);
        fragmentProdutos.setArguments(bundle);
        return fragmentProdutos;


    }
    @Override
    public int getCount() {
        return titles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}

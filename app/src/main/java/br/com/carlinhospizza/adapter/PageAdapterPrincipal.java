package br.com.carlinhospizza.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.carlinhospizza.fragments.FragmentPrincipal;
import br.com.carlinhospizza.models.Pedido;

public class PageAdapterPrincipal extends FragmentStatePagerAdapter {
    private String[] titles, titlesTab1, titlesTab2;
    private FragmentPrincipal fragmentPrincipal;
    private Pedido pedido;
    public PageAdapterPrincipal(FragmentManager fm, String[] titles, String[] titlesTab1, String[] titlesTab2, Pedido pedido) {
        super(fm);
        this.titles=titles;
        this.titlesTab1=titlesTab1;
        this.titlesTab2=titlesTab2;
        this.pedido = pedido;

    }

    @Override
    public Fragment getItem(final int position) {
        switch (position){
            case 0:
                fragmentPrincipal = new FragmentPrincipal(titlesTab1,"Salgados", pedido);
                return fragmentPrincipal;
            case 1:
                fragmentPrincipal = new FragmentPrincipal(titlesTab2, "Bebidas", pedido);
                return fragmentPrincipal;
                default:
                    return null;
        }

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

    public FragmentPrincipal getFragmentPrincipal() {
        return fragmentPrincipal;
    }
}

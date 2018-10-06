package com.example.filipealves.carlinhospizza.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filipealves.carlinhospizza.CardViewProdutos;
import com.example.filipealves.carlinhospizza.R;
import com.example.filipealves.carlinhospizza.Splash;
import com.example.filipealves.carlinhospizza.adapter.RecycleViewAdapter;
import com.example.filipealves.carlinhospizza.models.Produto;
import java.util.ArrayList;
import java.util.List;

public class FragmentProdutos extends Fragment {
    static List<CardViewProdutos> cardViewProdutosList;
    RecyclerView recyclerView;
    static  List<Produto> produtos;
    String title = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_produtos,container,false);
        cardViewProdutosList = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle!= null){
            title = bundle.getString("title");
        }


        if (Splash.PRODUTOS != null) {
            for (Produto produto : Splash.PRODUTOS) {
                if (produto.getCategoria().equals(title))
                    cardViewProdutosList.add(new CardViewProdutos(produto.getNome(), produto.getDescricao(), "R$" + produto.getValor(), R.drawable.img2));
            }

        }
        recyclerView = view.findViewById(R.id.recyclerViewProdutos);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(new RecycleViewAdapter(getActivity(), cardViewProdutosList));

        return view;
    }

    public static  List<CardViewProdutos> getCardViewProdutosList() {
        return cardViewProdutosList;
    }

    public static List<Produto> getProdutos(){
        return produtos;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}

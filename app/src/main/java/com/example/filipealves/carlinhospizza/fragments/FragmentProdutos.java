package com.example.filipealves.carlinhospizza.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.filipealves.carlinhospizza.CardViewProdutos;
import com.example.filipealves.carlinhospizza.MainActivity;
import com.example.filipealves.carlinhospizza.R;
import com.example.filipealves.carlinhospizza.Splash;
import com.example.filipealves.carlinhospizza.adapter.PageAdapterPrincipal;
import com.example.filipealves.carlinhospizza.adapter.RecycleViewAdapter;
import com.example.filipealves.carlinhospizza.models.Produto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
                Log.d("", produto.getNome());
                if (produto.getCategoria().equals(title)) {
                    Calendar calendar = new GregorianCalendar();
                    if(calendar.DAY_OF_WEEK==calendar.FRIDAY){
                        if (produto.getCategoria().equals("Pizzas")&&produto.getTamanho().equals("Media")){
                            produto.setValor("20.00");

                        }
                    }

                    CardViewProdutos cardViewProdutos = new CardViewProdutos();
                    cardViewProdutos.setNome(produto.getNome());
                    cardViewProdutos.setDescricao(produto.getDescricao());
                    cardViewProdutos.setValor("R$"+produto.getValor());
                    cardViewProdutos.setURLimagem(produto.getURLImagem());

                    cardViewProdutosList.add(cardViewProdutos);


                }

            }

        }else{
            new AlertDialog.Builder(getContext())
                    .setTitle("Erro no servidor")
                    .setMessage("Não foi possível conectar-se ao servidor")
                    .setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(getActivity(), Splash.class);
                                    getActivity().finish();
                                    startActivity(intent);
                                }

                            }).show();
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

package br.com.carlinhospizza.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.carlinhospizza.CardViewProdutos;

import com.example.filipealves.carlinhospizza.R;
import br.com.carlinhospizza.Splash;
import br.com.carlinhospizza.adapter.RecycleViewAdapter;
import br.com.carlinhospizza.models.Pedido;
import br.com.carlinhospizza.models.Produto;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class FragmentProdutos extends Fragment {
    private List<CardViewProdutos> cardViewProdutosList;
    private RecyclerView recyclerView;
    private Pedido pedido;
    private String title = null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_produtos,container,false);
        cardViewProdutosList = new ArrayList<>();
        Bundle bundle = getArguments();
        pedido =(Pedido)bundle.get("pedido");
        if (bundle!= null){
            title = bundle.getString("title");
        }


        if (Splash.PRODUTOS != null) {
            for (Produto produto : Splash.PRODUTOS) {
                Log.d("", produto.getNome());
                if (produto.getCategoria().equals(title)) {
                    Calendar calendar = new GregorianCalendar();
//                    if(calendar.DAY_OF_WEEK==calendar.SATURDAY){
//                        if (produto.getCategoria().equals("Pizzas")&&produto.getTamanho().equals("grande")){
//                            produto.setValor("20.00");
//
//                        }
//                    }

                    CardViewProdutos cardViewProdutos = new CardViewProdutos();
                    cardViewProdutos.setFatias(produto.getFatias());
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
        recyclerView.setAdapter(new RecycleViewAdapter(getActivity(), cardViewProdutosList, pedido));

        return view;
    }

    public List<CardViewProdutos> getCardViewProdutosList() {
        return cardViewProdutosList;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }



}

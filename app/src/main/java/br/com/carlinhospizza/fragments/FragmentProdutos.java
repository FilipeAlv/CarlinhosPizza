package br.com.carlinhospizza.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import br.com.carlinhospizza.activity.CardViewProdutos;

import com.example.filipealves.carlinhospizza.R;
import br.com.carlinhospizza.activity.Splash;
import br.com.carlinhospizza.adapter.RecycleViewAdapter;
import br.com.carlinhospizza.models.Produto;
import br.com.carlinhospizza.retrofit.RetrofitConfig;
import br.com.carlinhospizza.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class FragmentProdutos extends Fragment {

    RecyclerView recyclerView;
    static  List<Produto> produtos;
    String title = null;

    ArrayList<CardViewProdutos> cardViewProdutosList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_produtos,container,false);
        cardViewProdutosList = new ArrayList<>();
        Bundle bundle = getArguments();
        if (bundle!= null){
            title = bundle.getString("title");
        }


        if (Util.PRODUTOS != null) {
            for (Produto produto : Util.PRODUTOS) {
                Log.d("", produto.getNome());
                if (produto.getCategoria().equals(title)) {

                    CardViewProdutos cardViewProdutos = new CardViewProdutos();
                    cardViewProdutos.setNome(produto.getNome());
                    cardViewProdutos.setDescricao(produto.getDescricao());
                    cardViewProdutos.setValor("R$"+produto.getValor());
                    cardViewProdutos.setURLimagem(produto.getURLImagem());
                    if(produto.getStatus().equals("Promocao"))
                        cardViewProdutos.setPromocao(true);
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




    public static List<Produto> getProdutos(){
        return produtos;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }



}

package br.com.carlinhospizza.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.carlinhospizza.CardViewProdutos;

import com.example.filipealves.carlinhospizza.R;
import br.com.carlinhospizza.controller.Controller;

import java.util.ArrayList;
import java.util.List;

import br.com.carlinhospizza.Util.Util;
import br.com.carlinhospizza.models.Pedido;


public class RecycleViewAdapter extends RecyclerView.Adapter <RecycleViewAdapter.MyViewHolder>{

    private Context mContext;
    public static List<CardViewProdutos> mCardViewProdutosList;
    public static ArrayList<MyViewHolder> HOLDERS = new ArrayList<>();
    private Pedido pedido;

    public RecycleViewAdapter(Context mContext, List<CardViewProdutos> mCardViewProdutosList, Pedido pedido) {
        this.mContext = mContext;
        this.mCardViewProdutosList = mCardViewProdutosList;
        this.pedido = pedido;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater= LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_produtos, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
            holder.tvNome.setText(mCardViewProdutosList.get(position).getNome());
            holder.tvDescricao.setText(mCardViewProdutosList.get(position).getDescricao());
            holder.tvValor.setText(mCardViewProdutosList.get(position).getValor());
            if(mCardViewProdutosList.get(position).getFatias().equals("vazio"))
                holder.tvFatias.setVisibility(View.GONE);
            else
                holder.tvFatias.setText(mCardViewProdutosList.get(position).getFatias());

            Util.carregarImagem(holder.imgProduto, mCardViewProdutosList.get(position).getURLImagem());

            holder.fab.setOnClickListener( new Controller(pedido, holder, position, mCardViewProdutosList));
            holder.imgProduto.setOnClickListener( new Controller(pedido, holder, position, mCardViewProdutosList));
            HOLDERS.add(holder);


    }


    @Override
    public int getItemCount() {
        return mCardViewProdutosList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvNome, tvDescricao, tvValor, tvFatias;
        public ImageView imgProduto;
        public ImageButton fab;
        public MyViewHolder(View itemView) {
            super(itemView);

            tvNome = (TextView) itemView.findViewById(R.id.txtNomeCard);
            tvDescricao = (TextView) itemView.findViewById(R.id.txtDescricaoCard);
            tvValor = (TextView) itemView.findViewById(R.id.txtValorCard);
            imgProduto =(ImageView) itemView.findViewById(R.id.imgProduto);
            fab = (ImageButton) itemView.findViewById(R.id.fab);
            tvFatias = itemView.findViewById(R.id.txtFatias);


        }
    }
}

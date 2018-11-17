package br.com.carlinhospizza.adapter;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import br.com.carlinhospizza.activity.CardViewProdutos;

import com.example.filipealves.carlinhospizza.R;
import br.com.carlinhospizza.controller.Controller;

import java.util.ArrayList;
import java.util.List;

import br.com.carlinhospizza.util.Util;


public class RecycleViewAdapter extends RecyclerView.Adapter <RecycleViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<CardViewProdutos> mCardViewProdutosList;
    public static ArrayList<MyViewHolder> HOLDERS = new ArrayList<>();

    public RecycleViewAdapter(Context mContext, List<CardViewProdutos> mCardViewProdutosList) {
        this.mContext = mContext;
        this.mCardViewProdutosList = mCardViewProdutosList;

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
            Drawable background = holder.card.getBackground();
           // Util.carregarImagem(holder.imgProduto, mCardViewProdutosList.get(position).getURLImagem());
            if(mCardViewProdutosList.get(position).isPromocao())
                holder.imgPromo.setVisibility(View.VISIBLE);
            holder.fab.setOnClickListener( new Controller(Util.PEDIDO, holder, position, mCardViewProdutosList, background));
            holder.card.setOnClickListener( new Controller(Util.PEDIDO, holder, position, mCardViewProdutosList, background));
            HOLDERS.add(holder);
    }


    @Override
    public int getItemCount() {
        return mCardViewProdutosList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tvNome, tvDescricao, tvValor;
        public ImageView imgProduto, imgPromo;
        public ImageButton fab;
        public CardView card;
        public MyViewHolder(View itemView) {
            super(itemView);

            tvNome = (TextView) itemView.findViewById(R.id.txtNomeCard);
            tvDescricao = (TextView) itemView.findViewById(R.id.txtDescricaoCard);
            tvValor = (TextView) itemView.findViewById(R.id.txtValorCard);
            //imgProduto =(ImageView) itemView.findViewById(R.id.imgProduto);
            fab = (ImageButton) itemView.findViewById(R.id.fab);
            card = itemView.findViewById(R.id.fundoCard);
            imgPromo = itemView.findViewById(R.id.imgPromocao);


        };
    }
}

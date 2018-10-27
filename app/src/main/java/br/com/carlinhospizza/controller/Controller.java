package br.com.carlinhospizza.controller;

import android.view.View;
import android.widget.ImageButton;
import br.com.carlinhospizza.CardViewProdutos;
import br.com.carlinhospizza.MainActivity;
import com.example.filipealves.carlinhospizza.R;
import br.com.carlinhospizza.Splash;
import br.com.carlinhospizza.models.Pedido;
import br.com.carlinhospizza.models.Produto;
import br.com.carlinhospizza.adapter.RecycleViewAdapter;
import java.util.List;


public class Controller implements View.OnClickListener {
    private RecycleViewAdapter.MyViewHolder holder;
    private List<CardViewProdutos> mCardViewProdutosList;
    private int position;
    private Pedido pedido;
    private ImageButton fabPedidios;



    public Controller( Pedido pedido, RecycleViewAdapter.MyViewHolder holder, int position,  List<CardViewProdutos> mCardViewProdutosList){
        this.holder = holder;
        this.position = position;
        this.mCardViewProdutosList = mCardViewProdutosList;
        this.pedido=pedido;
        fabPedidios = MainActivity.getFavProdutos();
    }

    @Override
    public void onClick(View view) {
        if (holder.fab.getTag().equals("disponivel")) {
            holder.fab.setImageResource(R.drawable.fab_selecionado_24dp);
            holder.fab.setTag("selecionado");
            adicionarProduto(mCardViewProdutosList.get(position).getNome());
        }else if (holder.fab.getTag().equals("selecionado")){
            holder.fab.setImageResource(R.drawable.fab_disponivel_24dp);
            holder.fab.setTag("disponivel");
            removerProduto(mCardViewProdutosList.get(position).getNome());

        }
    }

    private void adicionarProduto(String nome){
            for (Produto produto : Splash.PRODUTOS) {
                if (produto.getNome().equals(nome)) {
                    pedido.getProdutos().add(produto);
                    break;
                }
            }


        if (pedido.getProdutos().size()==1){
            fabPedidios.setBackgroundResource(R.drawable.ic_pedidos_ativo);
        }
    }

    private void removerProduto(String nome){
        for (Produto produto:Splash.PRODUTOS) {
            if (produto.getNome().equals(nome)){
                pedido.getProdutos().remove(produto);
                break;
            }
        }


        if (pedido.getProdutos().size()==0){
            fabPedidios.setBackgroundResource(R.drawable.ic_pedidos);
        }

    }
}

package br.com.carlinhospizza.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import br.com.carlinhospizza.meus_pedidos;

import com.example.filipealves.carlinhospizza.R;
import br.com.carlinhospizza.models.Produto;

import java.util.ArrayList;

public class listAdapterAdicionarSabor extends ArrayAdapter<Produto> {
    private final Context context;
    private final ArrayList<Produto> elementos;
    Produto produto;

    public listAdapterAdicionarSabor(Context context, ArrayList<Produto> elementos,  Produto produto) {
        super(context, R.layout.listview_model, elementos);
        this.context=context;
        this.elementos=elementos;
        this.produto = produto;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.model_list_sabores, parent, false);

        final TextView nomeProduto = view.findViewById(R.id.listSaborNome);
        final TextView valorProduto = view.findViewById(R.id.listValorSaborProduto);
        final ImageButton iconDelete = view.findViewById(R.id.listIconAdicionar);
        final TextView descricaoProduto = view.findViewById(R.id.listSaborDescricao);

        nomeProduto.setText(elementos.get(position).getNome());
        valorProduto.setText("R$"+elementos.get(position).getValor());
        descricaoProduto.setText(elementos.get(position).getDescricao());
        iconDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        new AlertDialog.Builder(view.getContext())
                                .setTitle("Adicionar Sabor")
                                .setMessage("Ao adicionar um sabor o valor da pizza será o maior valor entre as duas")
                                .setPositiveButton("Ok",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                if (Double.parseDouble(elementos.get(position).getValor()) >
                                                        Double.parseDouble(produto.getValor())) {
                                                    meus_pedidos.valorT -= Double.parseDouble(produto.getValor());
                                                    meus_pedidos.valorT+= Double.parseDouble(elementos.get(position).getValor());
                                                    meus_pedidos.valorTotal.setText("R$" + meus_pedidos.valorT + "0");
                                                }
                                                produto.setObservacao("Metade " + produto.getNome() + ", metade "+ elementos.get(position).getNome());
                                                Intent intent = new Intent(context, meus_pedidos.class);
                                                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                                context.startActivity(intent);
                                                ((Activity) context).finish();

                                            }

                                        })
                                .setNegativeButton("Cancelar", null)
                                .show();
                    }
        });

        return view;
    }


}
package com.example.filipealves.carlinhospizza.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.filipealves.carlinhospizza.R;
import com.example.filipealves.carlinhospizza.models.Produto;
import java.util.ArrayList;

import Util.Util;

public class listAdapter extends ArrayAdapter<Produto> {
    private final Context context;
    private final ArrayList<Produto> elementos;
    private double valorT;
    private TextView valorTotal;

    public listAdapter(Context context, ArrayList<Produto> elementos, double valorT, TextView valorTotal) {
        super(context, R.layout.listview_model, elementos);
        this.context=context;
        this.elementos=elementos;
        this.valorT = valorT;
        this.valorTotal = valorTotal;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_model, parent, false);

        ImageView imageProduto = view.findViewById(R.id.listImageProduto);
        Util.carregarImagem(imageProduto, elementos.get(position).getURLImagem());
        final TextView nomeProduto = view.findViewById(R.id.listNomeProduto);
        final TextView valorProduto = view.findViewById(R.id.listValorProduto);
        final ImageButton iconDelete = view.findViewById(R.id.listIconDelete);
        final ViewHolderList holderList = new ViewHolderList(view);

        nomeProduto.setText(elementos.get(position).getNome());
        valorProduto.setText("R$"+elementos.get(position).getValor());
        iconDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (RecycleViewAdapter.MyViewHolder hold: RecycleViewAdapter.HOLDERS) {
                    final RecycleViewAdapter.MyViewHolder holder = hold;
                    if (holder.tvNome.getText().equals(elementos.get(position).getNome())){
                        new AlertDialog.Builder(view.getContext())
                                .setTitle("Excluir pedido")
                                .setMessage("Tem certeza que deseja excluir esse pedido?")
                                .setPositiveButton("Sim",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                holder.fab.setImageResource(R.drawable.fab_disponivel_24dp);
                                                holder.fab.setTag("disponivel");
                                                valorT -= Double.parseDouble(elementos.get(position).getValor())*Integer.parseInt(holderList.qnt_text.getText().toString());
                                                valorTotal.setText("R$" + valorT + "0");
                                                elementos.remove(elementos.get(position));
                                                notifyDataSetChanged();
                                            }

                                        })
                                .setNegativeButton("não", null)
                                .show();

                        break;
                    }
                }
            }
        });

        holderList.qnt_menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quant = Integer.parseInt(holderList.qnt_text.getText().toString());
                if(quant<=1){
                    new AlertDialog.Builder(view.getContext())
                            .setMessage("Esta é a quantidade minima para este pedido. ")
                            .setPositiveButton("OK", null)
                            .show();
                }else{
                    quant--;
                    elementos.get(position).setQuantidade(quant);
                    holderList.qnt_text.setText(""+quant);
                    valorT-= Double.parseDouble(elementos.get(position).getValor());
                    valorTotal.setText("R$" + valorT + "0");
                }
            }
        });

        holderList.qnt_mais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quant = Integer.parseInt(holderList.qnt_text.getText().toString());
                    quant++;
                    elementos.get(position).setQuantidade(quant);
                    holderList.qnt_text.setText(""+quant);
                    valorT+= Double.parseDouble(elementos.get(position).getValor());
                    valorTotal.setText("R$" + valorT + "0");

            }
        });

        holderList.observacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Alterar Pedido")
                        .setMessage("Você pode adicionar mais sabores a sua pizza ou remover alguns ingredientes")
                        .setPositiveButton("Adicionar",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent();
                                        getContext().startActivity(intent);
                                    }

                                }
                                )
                        .setNegativeButton("Remover",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext())
                                                .setTitle("Remover Ingredientes");
                                        final EditText input = new EditText(getContext());
                                        input.setHint("Ex.: Sem ervilha, sem tomate");
                                        alertDialog.setView(input);
                                        alertDialog.setPositiveButton("Salvar",
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                        elementos.get(position).setObservacao(input.getText().toString());
                                                    }
                                                })
                                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                    }
                                                });
                                    }
                                }).show();
            }
        });

        return view;
    }

    public class ViewHolderList {

        final TextView qnt_mais;
        final TextView qnt_menos;
        final TextView qnt_text;
        final ImageView  observacao;

        public ViewHolderList(View view) {
            qnt_mais = (TextView) view.findViewById(R.id.qnt_mais);
            qnt_menos = (TextView) view.findViewById(R.id.qnt_menos);
            qnt_text = view.findViewById(R.id.Quantidade);
            observacao = view.findViewById(R.id.Observacao);
        }

    }
}
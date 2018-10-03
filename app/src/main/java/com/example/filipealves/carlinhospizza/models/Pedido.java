package com.example.filipealves.carlinhospizza.models;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int id;
    private String descricao;
    private Date data;
    private int cliente_id;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private double valorTotal;


    public ArrayList<Produto>  getProdutos(){
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos){
        this.produtos = produtos;
    }
}




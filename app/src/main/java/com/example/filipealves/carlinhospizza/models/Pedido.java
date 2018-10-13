package com.example.filipealves.carlinhospizza.models;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int id;
    private String status;
    private String descricao;
    private String data;
    private int cliente_id;
    private int endereco_id;
    private String forma_pagamento;
    private String troco;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private String valor;


    public ArrayList<Produto>  getProdutos(){
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos){
        this.produtos = produtos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public String getTroco() {
        return troco;
    }

    public void setTroco(String troco) {
        this.troco = troco;
    }

    public String getValor() {
        return valor;
    }

    public int getEndereco_id() {
        return endereco_id;
    }

    public void setEndereco_id(int endereco_id) {
        this.endereco_id = endereco_id;
    }
}




package com.example.filipealves.carlinhospizza.models;

public class Produto {
    int id;
    String nome, descricao, valor, status, tipo, categoria;

    public String getNome(){
        return  nome;
    }
    public String getDescricao(){
        return  descricao;
    }
    public String getValor(){
        return valor;
    }
    public String getStatus(){return status; }
    public String getTipo(){ return tipo; }
    public String getCategoria(){ return categoria; }
}




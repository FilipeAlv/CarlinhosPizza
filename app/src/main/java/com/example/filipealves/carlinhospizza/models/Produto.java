package com.example.filipealves.carlinhospizza.models;

public class Produto {
    int id;
    String nome, descricao, valor, status, tipo, categoria, URLImagem;

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

    public String getURLImagem() {
        return URLImagem;
    }

    public void setURLImagem(String URLImagem) {
        this.URLImagem = URLImagem;

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}




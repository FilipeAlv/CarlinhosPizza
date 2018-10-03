package com.example.filipealves.carlinhospizza;

public class CardViewProdutos {
     private String nome, descricao, valor;
     private int imagem;

     public CardViewProdutos(String nome, String descricao, String valor, int imagem){
         this.nome=nome;
         this.descricao=descricao;
         this.valor=valor;
         this.imagem =imagem;

     }


    public int getImagem(){
         return imagem;
     }

     public void setImagem(int imagem){
         this.imagem=imagem;
     }
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getValor() {
        return valor;
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
}

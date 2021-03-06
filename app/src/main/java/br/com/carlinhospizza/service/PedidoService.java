package br.com.carlinhospizza.service;

import br.com.carlinhospizza.models.PedidoRet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PedidoService {
        @GET("app/cadastro_pedido.php")
        Call<PedidoRet> insertPedido(
                @Query("descricao") String descricao,
                @Query("valor") String valor,
                @Query("forma_pagamento") String forma_pagamento,
                @Query("troco") String troco,
                @Query("cliente_id") int cliente_id,
                @Query("endereco_id") int endereco_id

        );

        @GET("app/cadastro_pedido.php")
        Call<PedidoRet> insertPedidoEndereco(
                @Query("descricao") String descricao,
                @Query("valor") String valor,                                                   
                @Query("forma_pagamento") String forma_pagamento,
                @Query("troco") String troco,
                @Query("cliente_id") int cliente_id,
                @Query("rua") String rua,
                @Query("numero") String numero,
                @Query("bairro") String bairro,
                @Query("cidade") String cidade,
                @Query("cep") String cep,
                @Query("ponto_referencia") String pnt_ref


        );

        @GET("app/cadastro_pedido_produto.php")
        Call<PedidoRet> insertPedidoProduto(
                @Query("pedido_id") int pedido_id,
                @Query("produto_id") int produto_id,
                @Query("quantidade") int quantidade,
                @Query("observacao") String observacao


        );



}
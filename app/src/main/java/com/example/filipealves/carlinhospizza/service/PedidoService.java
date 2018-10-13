package com.example.filipealves.carlinhospizza.service;

import com.example.filipealves.carlinhospizza.models.Endereco;
import com.example.filipealves.carlinhospizza.models.Pedido;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PedidoService {
        @GET("app/cadastro_cliente.php")
        Call<Pedido> insertPedido(
                @Query("descricao") String descricao,
                @Query("valor") String valor,
                @Query("forma_pagamento") String forma_pagamento,
                @Query("troco") String troco,
                @Query("cliente_id") int cliente_id,
                @Query("endereco_id") int endereco_id

        );

}
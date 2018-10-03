package com.example.filipealves.carlinhospizza.service;

import com.example.filipealves.carlinhospizza.models.Produto;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProdutosService {
        @GET("app/listar_produtos.php")
        Call<List<Produto>> listProdutos();

        @GET("app/listar_produto_por_nome.php")
        Call<Produto> listProdutoPorNome(@Query("nome") String nome);

    }

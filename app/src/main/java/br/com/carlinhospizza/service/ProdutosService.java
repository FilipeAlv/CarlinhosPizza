package br.com.carlinhospizza.service;

import br.com.carlinhospizza.models.Produto;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProdutosService {
    @GET("app/listar_produtos.php")
    Call<List<Produto>> listProdutos();

    @GET("app/listar_mais_produtos.php")
    Call<List<Produto>> listProdutos(@Query("categoria") String categoria);

    @GET("app/listar_produto_por_nome.php")
    Call<Produto> listProdutoPorNome(@Query("nome") String nome);

    }

package br.com.carlinhospizza.service;

import br.com.carlinhospizza.models.Cliente;
import br.com.carlinhospizza.models.ClienteRet;
import br.com.carlinhospizza.models.Endereco;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClienteService {
        @GET("app/cadastro_cliente.php")
        Call<String> insertUser(
                @Query("rua") String rua,
                @Query("numero") String numero,
                @Query("bairro") String bairro,
                @Query("cidade") String cidade,
                @Query("cep") String cep,
                @Query("ponto_referencia") String pnt_ref,
                @Query("nome") String nome,
                @Query("cpf") String cpf,
                @Query("rg") String rg,
                @Query("data_nascimento") String dt_nasc,
                @Query("login") String login,
                @Query("senha") String senha,
                @Query("telefone") String telefone
        );

        @GET("app/listar_endereco_login.php")
        Call<Endereco> buscarEndereco(@Query("login") String login );

        @GET("app/listar_cliente_login.php")
        Call<ClienteRet> buscarCliente(@Query("login") String login );

        @GET("app/listar_cliente_login_senha.php")
        Call<ClienteRet> validarCliente(@Query("login") String login, @Query("senha") String senha);

}
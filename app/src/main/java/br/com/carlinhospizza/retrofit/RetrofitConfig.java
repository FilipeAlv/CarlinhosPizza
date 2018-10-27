package br.com.carlinhospizza.retrofit;


import br.com.carlinhospizza.service.ClienteService;
import br.com.carlinhospizza.service.PedidoService;
import br.com.carlinhospizza.service.ProdutosService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
   private final Retrofit retrofit;


   public RetrofitConfig() {
      this.retrofit = new Retrofit.Builder()
              .baseUrl("http://carlinhospizza.com/")
              .addConverterFactory(GsonConverterFactory.create())
              .build();
   }

   public ProdutosService getProdutosService() {
      return this.retrofit.create(ProdutosService.class);
   }

   public ClienteService getClienteService() {
      return this.retrofit.create(ClienteService.class);
   }

   public PedidoService getPedidoService() {
      return this.retrofit.create(PedidoService.class);
   }

}
package com.example.filipealves.carlinhospizza.retrofit;


import com.example.filipealves.carlinhospizza.service.ProdutosService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
   private final Retrofit retrofit;


   public RetrofitConfig() {
      this.retrofit = new Retrofit.Builder()
              .baseUrl("http://carlinhospizza.000webhostapp.com/")
              .addConverterFactory(GsonConverterFactory.create())
              .build();
   }

   public ProdutosService getProdutosService() {
      return this.retrofit.create(ProdutosService.class);
   }

}
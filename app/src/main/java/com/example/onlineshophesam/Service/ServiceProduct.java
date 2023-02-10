package com.example.onlineshophesam.Service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

import androidx.annotation.Nullable;

import com.example.onlineshophesam.Api.OnlineShopApi;
import com.example.onlineshophesam.Const;
import com.example.onlineshophesam.DbHelper.DbHelperProduct;
import com.example.onlineshophesam.entity.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProduct extends IntentService {

    boolean chDB = false;

    public ServiceProduct() {
        super("ProductService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        //check DB is empty or no
        chDB = new DbHelperProduct(this).checkEmptyDataBase();
        if(!chDB){
            //create interface from retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(OnlineShopApi.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            //create interface from API
            OnlineShopApi api = retrofit.create(OnlineShopApi.class);

            //make request
            Call<ArrayList<Product>> request = api.getallProduct();

            //put into queue
            request.enqueue(new Callback<ArrayList<Product>>() {
                @Override
                public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                    if (response.code()==200){
                        ArrayList<Product> data = response.body();

                        for (Product item : data){
                            Product product = new Product(item.getIdProduct(),item.getTitleProduct(),item.getDetailProduct() ,
                                    item.getImgProduct() , item.getModelProduct(),item.getNumberPhone(),item.getValueProduct(),
                                    item.getName1(),item.getName2(),item.getName3(),item.getNazar1(),item.getNazar2(),item.getNazar3() ,
                                    item.getLike1(),item.getLike2(),item.getLike3(),item.getNumStar(),item.getFavorite()
                            );
                            new DbHelperProduct(ServiceProduct.this).insert(product);
                        }

                    }
                }
                @Override
                public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                }
            });

        }
        SystemClock.sleep(2500);

        sendBroadcast(new Intent(Const.ACTION_NAME));

    }
}

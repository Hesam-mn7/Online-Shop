package com.example.onlineshophesam.Api;

import com.example.onlineshophesam.entity.Music;
import com.example.onlineshophesam.entity.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OnlineShopApi {

    String BASE_URL = "https://run.mocky.io/";

    @GET("v3/09e2f6eb-627a-4042-a0a1-07a8ed418644")
    Call<ArrayList<Music>> getallMusic();

    @GET("v3/24c91bba-36a6-4204-b575-32d936ceb589")
    Call<ArrayList<Product>> getallProduct();
}

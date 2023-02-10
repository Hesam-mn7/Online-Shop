package com.example.onlineshophesam.entity;

import java.util.ArrayList;

public class Product {

    private String idProduct;
    private String titleProduct;
    private String detailProduct;
    private String imgProduct;
    private String modelProduct;
    private String numberPhone;
    private String valueProduct;
    private String name1;
    private String name2;
    private String name3;
    private String nazar1;
    private String nazar2;
    private String nazar3;
    private String like1;
    private String like2;
    private String like3;
    private String numStar;
    private String favorite;

    public Product(String idProduct, String titleProduct, String detailProduct, String imgProduct, String modelProduct,
                   String numberPhone, String valueProduct, String name1, String name2, String name3, String nazar1, String nazar2,
                   String nazar3, String like1, String like2, String like3, String numStar, String favorite) {
        this.idProduct = idProduct;
        this.titleProduct = titleProduct;
        this.detailProduct = detailProduct;
        this.imgProduct = imgProduct;
        this.modelProduct = modelProduct;
        this.numberPhone = numberPhone;
        this.valueProduct = valueProduct;
        this.name1 = name1;
        this.name2 = name2;
        this.name3 = name3;
        this.nazar1 = nazar1;
        this.nazar2 = nazar2;
        this.nazar3 = nazar3;
        this.like1 = like1;
        this.like2 = like2;
        this.like3 = like3;
        this.numStar = numStar;
        this.favorite = favorite;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }

    public String getDetailProduct() {
        return detailProduct;
    }

    public void setDetailProduct(String detailProduct) {
        this.detailProduct = detailProduct;
    }

    public String getImgProduct() {
        return imgProduct;
    }

    public void setImgProduct(String imgProduct) {
        this.imgProduct = imgProduct;
    }

    public String getModelProduct() {
        return modelProduct;
    }

    public void setModelProduct(String modelProduct) {
        this.modelProduct = modelProduct;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getValueProduct() {
        return valueProduct;
    }

    public void setValueProduct(String valueProduct) {
        this.valueProduct = valueProduct;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getNazar1() {
        return nazar1;
    }

    public void setNazar1(String nazar1) {
        this.nazar1 = nazar1;
    }

    public String getNazar2() {
        return nazar2;
    }

    public void setNazar2(String nazar2) {
        this.nazar2 = nazar2;
    }

    public String getNazar3() {
        return nazar3;
    }

    public void setNazar3(String nazar3) {
        this.nazar3 = nazar3;
    }

    public String getLike1() {
        return like1;
    }

    public void setLike1(String like1) {
        this.like1 = like1;
    }

    public String getLike2() {
        return like2;
    }

    public void setLike2(String like2) {
        this.like2 = like2;
    }

    public String getLike3() {
        return like3;
    }

    public void setLike3(String like3) {
        this.like3 = like3;
    }

    public String getNumStar() {
        return numStar;
    }

    public void setNumStar(String numStar) {
        this.numStar = numStar;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
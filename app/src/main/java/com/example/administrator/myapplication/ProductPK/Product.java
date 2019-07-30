package com.example.administrator.myapplication.ProductPK;

public class Product {
    private int ID;
    private String CodeProduct;
    private String NameProduct;
    private int PriceProduct;
    private String KindProduct;

    public Product(String codeProduct, String nameProduct, int priceProduct, String kindProduct) {
        this.CodeProduct = codeProduct;
        this.NameProduct = nameProduct;
        this.PriceProduct = priceProduct;
        this.KindProduct = kindProduct;
    }

    public Product(int iD, String codeProduct, String nameProduct, int priceProduct, String kindProduct) {
        this.ID = iD;
        this.CodeProduct = codeProduct;
        this.NameProduct = nameProduct;
        this.PriceProduct = priceProduct;
        this.KindProduct = kindProduct;
    }

    public Product() {
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int iD) {
        this.ID = iD;
    }

    public String getCodeProduct() {
        return this.CodeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.CodeProduct = codeProduct;
    }

    public String getNameProduct() {
        return this.NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.NameProduct = nameProduct;
    }

    public int getPriceProduct() {
        return this.PriceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.PriceProduct = priceProduct;
    }

    public String getKindProduct() {
        return this.KindProduct;
    }

    public void setKindProduct(String kindProduct) {
        this.KindProduct = kindProduct;
    }
}


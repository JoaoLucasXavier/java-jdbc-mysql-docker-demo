package com.jl.lab.models;

public class ProductModel {
    private int id;
    private String name;
    private Double price;

    public ProductModel(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public ProductModel(int id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}

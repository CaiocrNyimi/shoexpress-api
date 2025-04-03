package br.com.fiap.shoexpress_api.model;

import java.util.Random;

public class Shoes {

    private Long id;
    private String name;
    private String brand;
    private double price;

    public Shoes(Long id, String name, String brand, double price) {
        this.id = Math.abs(new Random().nextLong());
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

}

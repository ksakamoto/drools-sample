package com.example.drools;

public class Product {
    private String name;
    private String type;
    private double price;
    private double discount;

    public Product() {}

    public Product(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.discount = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscountedPrice() {
        return price - (price * discount / 100);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", discountedPrice=" + getDiscountedPrice() +
                '}';
    }
}
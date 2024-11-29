package com.example.demo.dto;

public class ProductDetail {
    private String Name;  // Đổi từ 'name' thành 'productName'
    private int quantity;
    private double price;

    // Constructor to initialize the fields
    public ProductDetail(String Name, int quantity, double price) {
        this.Name = Name;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

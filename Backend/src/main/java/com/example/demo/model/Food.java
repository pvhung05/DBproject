package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Food extends Product {

    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    public Food() {}

    public Food(String name, double price, int quantity,Date importDate, Date expiryDate) {
        super( name, price, quantity,importDate);  // Call the superclass (Product) constructor
        this.expiryDate = expiryDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}

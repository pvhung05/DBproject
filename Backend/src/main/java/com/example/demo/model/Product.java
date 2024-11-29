package com.example.demo.model;
import jakarta.persistence.*;
import java.util.Date;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date importDate;



    private int stockQuantity;
    private String name;
    private double price;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice; // Reference back to Invoice


    public Product() {}

    public Product(String name, double price, int quantity,Date importDate) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.importDate = importDate;


    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public Date getImportDate() {
        return importDate;
    }
    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }








}

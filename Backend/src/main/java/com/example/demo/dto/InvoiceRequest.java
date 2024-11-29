package com.example.demo.dto;

import java.util.Date;
import java.util.List;

public class InvoiceRequest {
    private Long id;  // ID hóa đơn (nếu có)
    private Date purchaseDate;  // Ngày mua
    private Long employeeId;
    private  String customerName;
    private  String phone;//
    private String address;// ID nhân viên
    private List<ProductDetail> products;  // Danh sách sản phẩm

    // Constructor
    public InvoiceRequest(Long id, Date purchaseDate, Long employeeId, List<ProductDetail> products,String customerName,String phone,String address) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.employeeId = employeeId;
        this.products = products;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public List<ProductDetail> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDetail> products) {
        this.products = products;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }

}
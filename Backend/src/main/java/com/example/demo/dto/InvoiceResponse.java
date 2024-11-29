package com.example.demo.dto;

import java.util.Date;
import java.util.List;

public class InvoiceResponse {
    private Long invoiceId;
    private String employeeName;
    private String customerName;
    private String phone;
    private String address;
    private Date purchaseDate; // Đảm bảo có tham số này
    private List<ProductDetail> products;
    private double totalAmount;
    private int purchaseCount;

    // Constructor với 5 tham số (bao gồm purchaseDate)
    public InvoiceResponse(Long invoiceId, String employeeName,String customerName, Date purchaseDate, List<ProductDetail> products, double totalAmount,String phone,String address,int purchaseCount) {
        this.invoiceId = invoiceId;
        this.employeeName = employeeName;
        this.purchaseDate = purchaseDate;
        this.products = products;

        this.totalAmount = totalAmount;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.purchaseCount = purchaseCount;
    }

    public InvoiceResponse(Long id, String purchaseDate, double getTotalAmount, String employeeName) {
    }


    // Getters and Setters
    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<ProductDetail> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDetail> products) {
        this.products = products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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
   public void setAddress(String address) {
        this.address = address;
   }
   public int getPurchaseCount() {
        return purchaseCount;
   }
   public void setPurchaseCount(int purchaseCount) {
        this.purchaseCount = purchaseCount;
   }
}
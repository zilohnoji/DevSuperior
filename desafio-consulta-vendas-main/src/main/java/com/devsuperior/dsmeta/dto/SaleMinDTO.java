package com.devsuperior.dsmeta.dto;

import java.time.LocalDate;

public class SaleMinDTO {

    private Long id;
    private Double amount;
    private LocalDate date;
    private String sellerName;

    public SaleMinDTO() {
    }

    public SaleMinDTO(Long id, Double amount, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.date = date;
    }

    public SaleMinDTO(Long id, LocalDate date, Double amount, String sellerName) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.sellerName = sellerName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}

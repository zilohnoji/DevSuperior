package com.donatoordep.dscommerce.dto;

import com.donatoordep.dscommerce.entities.OrderItem;

public class OrderItemDTO {

    private Long productId;
    private String name;
    private Integer quantity;
    private Double price;
    private String imgUrl;

    public OrderItemDTO(Long productId, String name, Integer quantity, Double price, String imgUrl) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public OrderItemDTO(OrderItem entity) {
        this.productId = getProductId();
        this.name = entity.getProduct().getName();
        this.quantity = entity.getQuantity();
        this.price = entity.getPrice();
        this.imgUrl = entity.getProduct().getImgUrl();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}

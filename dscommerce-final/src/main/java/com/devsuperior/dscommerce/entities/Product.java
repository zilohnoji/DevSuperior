package com.devsuperior.dscommerce.entities;

import java.util.*;

import com.devsuperior.dscommerce.builders.entities.ProductSpecificationBuilder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public final class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Double price;
    private String imgUrl;

    @ManyToMany
    @JoinTable(name = "tb_product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

    private Product() {
    }

    public static class ProductBuilder implements ProductSpecificationBuilder {
        private Product entity;

        private ProductBuilder() {
            this.reset();
        }

        public static ProductBuilder builder() {
            return new ProductBuilder();
        }

        @Override
        public Product build() {
            return this.entity;
        }

        public ProductBuilder id(Long id) {
            this.entity.setId(id);
            return this;
        }

        public ProductBuilder imgUrl(String imgUrl) {
            this.entity.setImgUrl(imgUrl);
            return this;
        }

        public ProductBuilder category(Category category) {
            this.entity.getCategories().add(category);
            return this;
        }

        public ProductBuilder categories(Set<Category> categories) {
            this.entity.setCategories(categories);
            return this;
        }

        @Override
        public ProductBuilder name(String name) {
            this.entity.setName(name);
            return this;
        }

        @Override
        public ProductBuilder description(String description) {
            this.entity.setDescription(description);
            return this;
        }

        @Override
        public ProductBuilder price(Double price) {
            this.entity.setPrice(price);
            return this;
        }

        @Override
        public void reset() {
            this.entity = new Product();
        }
    }

    private void setId(Long id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setPrice(Double price) {
        this.price = price;
    }

    private void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    private void setItems(Set<OrderItem> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public List<Order> getOrders() {
        return items.stream().map(OrderItem::getOrder).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
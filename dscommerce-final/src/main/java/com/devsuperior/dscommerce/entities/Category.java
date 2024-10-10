package com.devsuperior.dscommerce.entities;

import jakarta.persistence.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "tb_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products = new HashSet<>();

    private Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        if (this == o) return true;
        return Objects.equals(category.id, this.id);
    }

    @Override
    public int hashCode() {
        AtomicInteger result = new AtomicInteger(Long.hashCode(this.id));

        this.products.forEach(item -> {
            result.set(31 * result.get() + item.hashCode());
        });

        return result.get();
    }
}

package com.devsuperior.dscommerce.builders;

public interface Builder<T> {

    void reset();

    T build();
}
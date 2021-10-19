package com.webshop.core.models;

public class BaseProductSupplier implements ProductSupplier {
    private Long id;
    private String name;
    private Double rating;

    public BaseProductSupplier(Long id, String name, Double rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Double getRating() {
        return rating;
    }
}

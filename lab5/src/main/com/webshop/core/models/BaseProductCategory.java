package com.webshop.core.models;

public class BaseProductCategory implements ProductCategory {
    private Long id;
    private String name;

    public BaseProductCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}

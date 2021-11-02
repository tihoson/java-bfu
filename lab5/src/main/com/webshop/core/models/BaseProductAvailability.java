package com.webshop.core.models;

public class BaseProductAvailability implements ProductAvailability {
    private Long id;
    private Long productSupplierId;
    private Long categoryId;
    private String productName;
    private String productDescription;
    private Double productRating;
    private Double productPrice;
    private Long availableProductQuantity;

    // класс принимает большое количество параметров, однако т.к. в дальнейшем
    // данная задача не расширяет свой функционал, то делать фабричные методы для сбора "холодильников"
    // или билдеры считаю не особо нужным
    // особенно учитывая то, что данный класс реализуется только для тестовых соображений
    public BaseProductAvailability(Long id,
                                   Long productSupplierId,
                                   Long categoryId,
                                   String productName,
                                   String productDescription,
                                   Double productRating,
                                   Double productPrice,
                                   Long availableProductQuantity) {
        this.id = id;
        this.productSupplierId = productSupplierId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productRating = productRating;
        this.productPrice = productPrice;
        this.availableProductQuantity = availableProductQuantity;
    }

    @Override
    public Long getProductId() {
        return id;
    }

    @Override
    public Long getProductSupplierId() {
        return productSupplierId;
    }

    @Override
    public Long getCategoryId() {
        return categoryId;
    }

    @Override
    public String getProductName() {
        return productName;
    }

    @Override
    public String getProductDescription() {
        return productDescription;
    }

    @Override
    public Double getProductRating() {
        return productRating;
    }

    @Override
    public Double getProductPrice() {
        return productPrice;
    }

    @Override
    public Long getAvailableProductQuantity() {
        return availableProductQuantity;
    }
}

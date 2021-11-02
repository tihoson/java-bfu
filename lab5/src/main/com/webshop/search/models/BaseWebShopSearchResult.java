package com.webshop.search.models;

public class BaseWebShopSearchResult implements WebShopSearchResult{
    private final String productName;
    private final String productDescription;
    private final Double productPrice;
    private final Double productRating;
    private final String productCategoryName;
    private final String productSupplierName;
    private final Double productSupplierRating;

    public BaseWebShopSearchResult(String productName,
                                   String productDescription,
                                   Double productPrice,
                                   Double productRating,
                                   String productCategoryName,
                                   String productSupplierName,
                                   Double productSupplierRating) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productRating = productRating;
        this.productCategoryName = productCategoryName;
        this.productSupplierName = productSupplierName;
        this.productSupplierRating = productSupplierRating;
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
    public Double getProductPrice() {
        return productPrice;
    }

    @Override
    public Double getProductRating() {
        return productRating;
    }

    @Override
    public String getProductCategoryName() {
        return productCategoryName;
    }

    @Override
    public String getProductSupplierName() {
        return productSupplierName;
    }

    @Override
    public Double getProductSupplierRating() {
        return productSupplierRating;
    }
}

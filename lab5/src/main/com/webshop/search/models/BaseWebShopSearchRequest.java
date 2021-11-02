package com.webshop.search.models;

public class BaseWebShopSearchRequest implements WebShopSearchRequest {
    private final String productNameSearchText;
    private final Double maximalProductPrice;
    private final Double minimalProductRating;

    public BaseWebShopSearchRequest(String productNameSearchText,
                                    Double maximalProductPrice,
                                    Double minimalProductRating) {
        this.productNameSearchText = productNameSearchText;
        this.maximalProductPrice = maximalProductPrice;
        this.minimalProductRating = minimalProductRating;
    }

    @Override
    public String getProductNameSearchText() {
        return productNameSearchText;
    }

    @Override
    public Double getMaximalProductPrice() {
        return maximalProductPrice;
    }

    @Override
    public Double getMinimalProductRating() {
        return minimalProductRating;
    }
}

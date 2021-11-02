package com.webshop.core;

import com.webshop.core.models.ProductAvailability;
import com.webshop.core.models.ProductCategory;
import com.webshop.core.models.ProductSupplier;

import java.util.List;
import java.util.stream.Collectors;

public class BaseWebShop implements  WebShop{
    private List<ProductSupplier> productSuppliers;
    private List<ProductCategory> productCategories;
    private List<ProductAvailability> productAvailabilities;

    public BaseWebShop(List<ProductSupplier> productSuppliers,
                       List<ProductCategory> productCategories,
                       List<ProductAvailability> productAvailabilities) {
        this.productSuppliers = productSuppliers;
        this.productCategories = productCategories;
        this.productAvailabilities = productAvailabilities;
    }

    @Override
    public List<ProductSupplier> getProductSuppliers() {
        return this.productSuppliers;
    }

    @Override
    public List<ProductCategory> getProductCategories() {
        return this.productCategories;
    }

    @Override
    public List<ProductAvailability> getProducts(Long storeId, Long categoryId) {
        return productAvailabilities.stream()
                .filter(productAvailability -> productAvailability.getProductSupplierId().equals(storeId))
                .filter(productAvailability -> productAvailability.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }
}

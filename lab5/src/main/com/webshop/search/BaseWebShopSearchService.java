package com.webshop.search;

import com.webshop.core.WebShop;
import com.webshop.search.models.BaseWebShopSearchResult;
import com.webshop.search.models.WebShopSearchRequest;
import com.webshop.search.models.WebShopSearchResult;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BaseWebShopSearchService implements WebShopSearchService{
    private WebShop webShop;

    public BaseWebShopSearchService(WebShop webShop) {
        this.webShop = webShop;
    }

    @Override
    public List<WebShopSearchResult> findProducts(WebShopSearchRequest searchRequest) {
        return webShop.getProductCategories().stream()
                .flatMap(
                        productCategory -> this.webShop.getProductSuppliers().stream()
                                .flatMap(
                                        productSupplier -> this.webShop.getProducts(
                                                productSupplier.getId(), productCategory.getId()).stream()
                                                .filter(productAvailability -> productAvailability.getAvailableProductQuantity() > 0)
                                                .filter(productAvailability -> productAvailability.getProductName()
                                                        .contains(searchRequest.getProductNameSearchText()))
                                                .filter(productAvailability -> productAvailability.getProductPrice() <=
                                                        searchRequest.getMaximalProductPrice())
                                                .filter(productAvailability -> productAvailability.getProductRating() >=
                                                        searchRequest.getMinimalProductRating())
                                                .map(productAvailability -> new BaseWebShopSearchResult(
                                                        productAvailability.getProductName(),
                                                        productAvailability.getProductDescription(),
                                                        productAvailability.getProductPrice(),
                                                        productAvailability.getProductPrice(),
                                                        productCategory.getName(),
                                                        productSupplier.getName(),
                                                        productSupplier.getRating()
                                                ))
                                )
                ).sorted(
                        Comparator.comparing(WebShopSearchResult::getProductPrice)
                                .thenComparing(WebShopSearchResult::getProductRating).reversed()
                                .thenComparing(WebShopSearchResult::getProductSupplierRating).reversed()
                ).limit(20)
                .collect(Collectors.toList());
    }
}

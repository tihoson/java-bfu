package com.webshop;

import com.webshop.core.BaseWebShop;
import com.webshop.core.WebShop;
import com.webshop.core.models.*;
import com.webshop.search.BaseWebShopSearchService;
import com.webshop.search.WebShopSearchService;
import com.webshop.search.models.BaseWebShopSearchRequest;
import com.webshop.search.models.WebShopSearchResult;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class BaseWebShopSearchServiceTests {

    @Test
    public void shouldBeOdd() {
        List<ProductSupplier> productSuppliers = new ArrayList<>();
        productSuppliers.add(new BaseProductSupplier(1L, "supplier 1", 10.0));
        productSuppliers.add(new BaseProductSupplier(2L, "supplier 2", 5.0));

        List<ProductCategory> productCategories = new ArrayList<>();
        productCategories.add(new BaseProductCategory(1L, "teapots"));

        List<ProductAvailability> productAvailabilities = new ArrayList<>();

        long count = 50;
        for (long i = 0; i < count; i++) {
            productAvailabilities.add(
                    new BaseProductAvailability(i, 1 + i % 2, 1L,
                            "teapot" + Long.valueOf(i).toString(), "teapot" + Long.valueOf(i).toString(),
                            (double)i * (i % 2), (double)(count - i), i));
        }

        WebShop webShop = new BaseWebShop(productSuppliers, productCategories, productAvailabilities);
        WebShopSearchService webShopSearchService = new BaseWebShopSearchService(webShop);

        List<WebShopSearchResult> results = webShopSearchService.findProducts(
                new BaseWebShopSearchRequest("teapot", 20.0, 30.0));

        List<String> actual = results.stream()
                .map(WebShopSearchResult::getProductName)
                .collect(Collectors.toList());

        List<String> expected = new ArrayList<>();
        for (int i = 49; i > 30; i -= 2)
            expected.add("teapot" + Long.valueOf(i).toString());

        assertEquals(expected, actual);
    }
}

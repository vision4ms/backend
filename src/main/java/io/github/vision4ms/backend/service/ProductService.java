package io.github.vision4ms.backend.service;

import io.github.vision4ms.backend.models.dto.NewProductDTO;
import io.github.vision4ms.backend.models.entity.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(String id);
    List<Product> getAllProducts();
    Product saveProduct(NewProductDTO product);
    void deleteProduct(String id);
    List<Product> getProductsByName(String name);
}

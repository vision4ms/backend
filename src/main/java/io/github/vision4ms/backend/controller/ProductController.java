package io.github.vision4ms.backend.controller;

import io.github.vision4ms.backend.models.dto.NewProductDTO;
import io.github.vision4ms.backend.models.entity.Product;
import io.github.vision4ms.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RequestMapping("/api/v1/products")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    ResponseEntity<Product> getProduct(@PathVariable String id) {

        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping
    ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String name) {
        if (!Objects.isNull(name))
            return ResponseEntity.ok(productService.getProductsByName(name));

        return ResponseEntity.ok(productService.getAllProducts());
    }


    @PostMapping
    ResponseEntity<Product> saveProduct(@RequestBody NewProductDTO product) {
        var newProduct = productService.saveProduct(product);

        return ResponseEntity.created(URI.create("products/" + newProduct.getId())).body(newProduct);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);

        return ResponseEntity.ok().build();
    }
}

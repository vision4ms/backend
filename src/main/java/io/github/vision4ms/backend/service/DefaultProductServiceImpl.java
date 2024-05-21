package io.github.vision4ms.backend.service;

import io.github.vision4ms.backend.exceptions.NotFoundException;
import io.github.vision4ms.backend.models.dto.NewProductDTO;
import io.github.vision4ms.backend.models.entity.Product;
import io.github.vision4ms.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DefaultProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product getProduct(String id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product saveProduct(NewProductDTO product) {

        var today = LocalDateTime.now();
        var depends = new LinkedHashSet<Product>();

        for (var p : product.depends()) {
            var depend = new Product();

            depend.setDescription("Unknown");
            depend.setName(p);
            depend.setCreatedAt(today);
            depend.setUpdatedAt(today);

            productRepository.save(depend);
        }

        return productRepository.insert(adapt(product, depends));
    }

    private Product adapt(NewProductDTO product, Set<Product> products) {
        var p = new Product();

        p.setName(product.name());

        var today = LocalDateTime.now();

        p.setDescription(product.description());
        p.setCreatedAt(today);
        p.setUpdatedAt(today);

        if (!Objects.isNull(products)) p.setDepends(products);

        return p;
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findAllByNameContainingIgnoreCase(name);
    }
}

package com.sda.patricban.electronicsgeek.service;

import com.sda.patricban.electronicsgeek.model.Product;
import com.sda.patricban.electronicsgeek.model.enums.Brand;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getProductByPriceBetween(Double price1, Double price2);

    Optional<Product> getProductByIdProduct(Long idProduct);

    Optional<Product> findProductByName(String name);

    List<Product> findProductByCategory(String category);

    List<Product> findProductByBrand(Brand brand);

    Product updateProduct(Product product);

    Product addProduct(Product product);

    void deleteProductByIdProduct(Long idProduct);

}

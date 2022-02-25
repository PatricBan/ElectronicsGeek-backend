package com.sda.patricban.electronicsgeek.repository;

import com.sda.patricban.electronicsgeek.model.Product;
import com.sda.patricban.electronicsgeek.model.enums.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();

    Optional<Product> findByIdProduct(Long idProduct);

    Optional<Product> findByName(String name);

    List<Product> findByCategory(String category);

    List<Product> findByBrand(Brand brand);

    List<Product> findByPriceBetween(Double price1, Double price2);

}

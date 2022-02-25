package com.sda.patricban.electronicsgeek.service;

import com.sda.patricban.electronicsgeek.model.Product;
import com.sda.patricban.electronicsgeek.model.enums.Brand;
import com.sda.patricban.electronicsgeek.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByPriceBetween(Double price1, Double price2) {
        return productRepository.findByPriceBetween(price1, price2);
    }

    @Override
    public Optional<Product> getProductByIdProduct(Long idProduct) {
        Optional<Product> productFromDb = productRepository.findByIdProduct(idProduct);
        if(!(productFromDb.isPresent())){
            throw new IllegalArgumentException("Product does not exist");
        }
        return productFromDb;
    }

    @Override
    public Optional<Product> findProductByName(String name) {
        Optional<Product> productFromDb = productRepository.findByName(name);
        if(!(productFromDb.isPresent())){
            throw new IllegalArgumentException("Product with such name does not exist");
        }
        return productFromDb;
    }

    @Override
    public List<Product> findProductByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findProductByBrand(Brand brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productFromDb = productRepository.findByIdProduct(product.getIdProduct());
        if(!(productFromDb.isPresent())){
            throw new IllegalArgumentException("Product does not exist");
        }
        return productRepository.save(product);
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Product> productFromDb = productRepository.findByIdProduct(product.getIdProduct());
        if(productFromDb.isPresent()){
            throw new IllegalArgumentException("Product already exist");
        }
        return productRepository.save(product);
    }

    @Override
    public void deleteProductByIdProduct(Long idProduct) {
        Optional<Product> productFromDb = productRepository.findByIdProduct(idProduct);
        if(!productFromDb.isPresent()){
            throw new IllegalArgumentException("Product does not exist");
        }
        productRepository.deleteById(idProduct);
    }
}

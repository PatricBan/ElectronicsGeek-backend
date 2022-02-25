package com.sda.patricban.electronicsgeek.controller;

import com.sda.patricban.electronicsgeek.controller.dto.ProductDto;
import com.sda.patricban.electronicsgeek.model.Product;
import com.sda.patricban.electronicsgeek.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/products")
@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/allProducts")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> productList = productService.getAllProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @GetMapping("/getProduct/{idProduct}")
    public ResponseEntity<Optional<Product>> getProduct(@PathVariable Long idProduct){
        try {
            Optional<Product> productFromDb = productService.getProductByIdProduct(idProduct);
            return new ResponseEntity<>(productFromDb, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/createProduct")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product createdProduct = productService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.OK);
    }

    @PutMapping("/updateProduct/{idProduct}")
    public ResponseEntity<Product> updateProduct(@RequestBody ProductDto productDto, @PathVariable(value = "idProduct") Long idProduct) {
        Optional<Product> productFromDatabase = productService.getProductByIdProduct(idProduct);
        productFromDatabase.get().setName(productDto.getName());
        productFromDatabase.get().setDescription(productDto.getDescription());
        productFromDatabase.get().setPrice(productDto.getPrice());
        productFromDatabase.get().setBrand(productDto.getBrand());
        productService.updateProduct(productFromDatabase.get());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .build();
    }

    @DeleteMapping("deleteProduct/{idProduct}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long idProduct){
        try{
            productService.deleteProductByIdProduct(idProduct);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException exception){
            return ResponseEntity.notFound().build();
        }
    }
}

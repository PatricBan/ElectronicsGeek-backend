package com.sda.patricban.electronicsgeek.model;


import com.sda.patricban.electronicsgeek.model.enums.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "price")
    private Double price;

    @Column(name = "brand")
    @Enumerated(value = EnumType.STRING)
    private Brand brand;

    @ManyToMany(mappedBy = "productList", cascade = CascadeType.ALL)
    private Set<Order> orderSet;


}

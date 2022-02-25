package com.sda.patricban.electronicsgeek.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_order")
    private Long idOrder;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "address")
    private String address;

    @Column(name = "tracking_number", unique = true)
    @Size(min = 3, max = 40)
    private String tracking_number;

    @Column(name = "total_cost")
    private Double totalCost;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_product")
    )
    private Set<Product> productList;
}

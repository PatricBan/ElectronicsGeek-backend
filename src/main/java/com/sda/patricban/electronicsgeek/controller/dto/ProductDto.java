package com.sda.patricban.electronicsgeek.controller.dto;

import com.sda.patricban.electronicsgeek.model.enums.Brand;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private double price;

    @NotNull
    private Brand brand;

}

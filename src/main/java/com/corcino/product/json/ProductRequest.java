package com.corcino.product.json;

import lombok.Data;

@Data
public class ProductRequest {

    private String name;
    private Double price;
    private Integer quantity;
    private Long categoryId;

}

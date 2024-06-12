package com.corcino.product.json;

import lombok.Data;

@Data
public class ProductResponse {

    private String productId;
    private String name;
    private Double price;
    private Integer quantity;
    private CategoryResponse category;

}

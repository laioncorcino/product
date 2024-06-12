package com.corcino.product.controller;

import com.corcino.product.json.ProductRequest;
import com.corcino.product.json.ProductResponse;
import com.corcino.product.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> post(@RequestBody ProductRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(request));
    }

    @PutMapping("{productId}")
    public ResponseEntity<ProductResponse> put(@PathVariable("productId") Long productId,
                                                  @RequestBody ProductRequest request) throws Exception {
        return ResponseEntity.status(200)
                .body(productService.update(productId, request));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<ProductResponse> delete
            (@PathVariable("productId") Long productId) throws Exception {
        return ResponseEntity.status(200).body(productService.delete(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy)
            throws Exception {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.status(200)
                .body(productService.getAll(pageable));
    }

    @GetMapping("{productId}")
    public ResponseEntity<ProductResponse> getById(@PathVariable("productId") Long productId)
            throws Exception {
        return ResponseEntity.status(200).body(productService.getById(productId));
    }

}


package com.corcino.product.service;

import com.corcino.product.entity.Category;
import com.corcino.product.entity.Product;
import com.corcino.product.json.ProductRequest;
import com.corcino.product.json.ProductResponse;
import com.corcino.product.repository.CategoryRepository;
import com.corcino.product.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public ProductResponse create(ProductRequest request) throws Exception {
        Product product = modelMapper.map(request, Product.class);

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() -> new Exception("Category not found!"));

        product.setCategory(category);
        productRepository.save(product);

        return modelMapper.map(product, ProductResponse.class);
    }

    public ProductResponse update(Long id, ProductRequest request) throws Exception {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Product not found!"));

        Category category = categoryRepository
                .findById(request.getCategoryId())
                .orElseThrow(() -> new Exception("Category not found!"));

        product.setName(request.getName());
        product.setPrice(BigDecimal.valueOf(request.getPrice()));
        product.setQuantity(request.getQuantity());
        product.setCategory(category);

        productRepository.save(product);

        return modelMapper.map(product, ProductResponse.class);
    }

    public ProductResponse delete(Long id) throws Exception {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Product not found!"));
        productRepository.delete(product);
        return modelMapper.map(product, ProductResponse.class);
    }

    public List<ProductResponse> getAll
            (Pageable pageable) throws Exception {
        Page<Product> products = productRepository.findAll(pageable);

        return products
                .stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    public ProductResponse getById(Long id) throws Exception {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Product not found!"));
        return modelMapper.map(product, ProductResponse.class);
    }

}

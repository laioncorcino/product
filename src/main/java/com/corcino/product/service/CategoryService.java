package com.corcino.product.service;

import com.corcino.product.json.CategoryResponse;
import com.corcino.product.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> mapper.map(c, CategoryResponse.class))
                .collect(Collectors.toList());
    }


}

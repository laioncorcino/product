package com.corcino.product.config;

import com.corcino.product.entity.Category;
import com.corcino.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Autowired
    public DataInitializer(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) {
        List<String> names = Arrays.asList("Eletronics", "Books", "Sports", "Games", "Toys");
        for (String name : names) {
            if (!categoryRepository.existsByName(name)) {
                Category category = new Category();
                category.setName(name);

                categoryRepository.save(category);
            }
        }
    }

}

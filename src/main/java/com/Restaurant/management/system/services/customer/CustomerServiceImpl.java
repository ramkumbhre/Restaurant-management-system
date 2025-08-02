package com.Restaurant.management.system.services.customer;

import com.Restaurant.management.system.dtos.CategoryDto;
import com.Restaurant.management.system.dtos.ProductDto;
import com.Restaurant.management.system.entities.Category;
import com.Restaurant.management.system.entities.Product;
import com.Restaurant.management.system.repositories.CategoryRepository;
import com.Restaurant.management.system.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getCategoriesByName(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title) {
        return productRepository.findAllByCategoryIdAndNameContaining(categoryId, title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }
}

package com.Restaurant.management.system.services.customer;

import com.Restaurant.management.system.dtos.CategoryDto;
import com.Restaurant.management.system.dtos.ProductDto;

import java.util.List;

public interface CustomerService {

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getCategoriesByName(String title);

    List<ProductDto> getProductsByCategory(Long categoryId);

    List<ProductDto> getProductsByCategoryAndTitle(Long categoryId, String title);
}

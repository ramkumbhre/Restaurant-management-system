package com.Restaurant.management.system.services.admin;

import com.Restaurant.management.system.dtos.CategoryDto;
import com.Restaurant.management.system.dtos.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {

   CategoryDto postCategory(CategoryDto categoryDto) throws IOException;

   List<CategoryDto> getAllCategories();

   List<CategoryDto> getAllCategoriesByTitle(String title);


    ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException;

    List<ProductDto> getAllProductsByCategory(Long categoryId);

    List<ProductDto> getProductsByCategoryAndTitle(Long categoryId ,String title);

    void deleteProduct(Long productId);

    ProductDto getProductById(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException;
}


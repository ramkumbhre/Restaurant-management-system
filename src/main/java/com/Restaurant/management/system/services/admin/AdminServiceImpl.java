package com.Restaurant.management.system.services.admin;

import com.Restaurant.management.system.dtos.CategoryDto;
import com.Restaurant.management.system.entities.Category;
import com.Restaurant.management.system.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
 
    private final CategoryRepository categoryRepository;


    @Override
    public CategoryDto postCategory(CategoryDto categoryDto) throws IOException {
        Category category = new Category();

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImg(categoryDto.getImg().getBytes());

        Category createdCategory = categoryRepository.save(category);
        CategoryDto createdCategoryDto = new CategoryDto();
        createdCategoryDto.setId(createdCategory.getId());

        return createdCategoryDto;
    }
}

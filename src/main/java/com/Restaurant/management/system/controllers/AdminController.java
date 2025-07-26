package com.Restaurant.management.system.controllers;

import com.Restaurant.management.system.dtos.CategoryDto;
import com.Restaurant.management.system.dtos.ProductDto;
import com.Restaurant.management.system.services.admin.AdminService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @PostMapping("/category")
    public ResponseEntity<CategoryDto> postCategory(@ModelAttribute CategoryDto categoryDto) throws IOException {
        CategoryDto createdCategoryDto = adminService.postCategory(categoryDto);
        if(createdCategoryDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(createdCategoryDto);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtoList = adminService.getAllCategories();
        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByTitle(@PathVariable String title){
        List<CategoryDto> categoryDtoList = adminService.getAllCategoriesByTitle(title);
        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @PostMapping("/{categoryId}/product")
    public ResponseEntity<?> postProduct(@PathVariable Long categoryId, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto createdProductDto = adminService.postProduct(categoryId, productDto);
        if(createdProductDto == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDto);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategory(@PathVariable Long categoryId){
        List<ProductDto> productDtoList = adminService.getAllProductsByCategory(categoryId);
        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable Long categoryId, @PathVariable String title){
        List<ProductDto> productDtoList = adminService.getProductsByCategoryAndTitle(categoryId,title);
        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
         adminService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}

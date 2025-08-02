package com.Restaurant.management.system.controllers;


import com.Restaurant.management.system.dtos.CategoryDto;
import com.Restaurant.management.system.dtos.ProductDto;
import com.Restaurant.management.system.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;


    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtoList = customerService.getAllCategories();
        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getCategoriesByName(@PathVariable String title){
        List<CategoryDto> categoryDtoList = customerService.getCategoriesByName(title);
        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    // Product Operation

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable Long categoryId){
        List<ProductDto> productDtoList = customerService.getProductsByCategory(categoryId);
        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductsByCategoryAndTitle(@PathVariable Long categoryId, @PathVariable String title){
        List<ProductDto> productDtoList = customerService.getProductsByCategoryAndTitle(categoryId,title);
        if(productDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoList);
    }
}

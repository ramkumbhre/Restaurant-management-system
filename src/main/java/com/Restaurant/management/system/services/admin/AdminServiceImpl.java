package com.Restaurant.management.system.services.admin;

import com.Restaurant.management.system.dtos.CategoryDto;
import com.Restaurant.management.system.dtos.ProductDto;
import com.Restaurant.management.system.entities.Category;
import com.Restaurant.management.system.entities.Product;
import com.Restaurant.management.system.repositories.CategoryRepository;
import com.Restaurant.management.system.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{
 
    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;


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

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getAllCategoriesByTitle(String title) {
        return categoryRepository.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if(optionalCategory.isPresent()){
            Product product = new Product();
            BeanUtils.copyProperties(productDto, product);
            product.setImg(productDto.getImg().getBytes());

//            System.out.println("Image size in bytes: " + productDto.getImg().getSize());

            product.setCategory(optionalCategory.get());

            Product createdProduct = productRepository.save(product);
            ProductDto createdProductDto = new ProductDto();
            createdProductDto.setId(createdProduct.getId());

            return createdProductDto;
        }
        return null;
    }

    @Override
    public List<ProductDto> getAllProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByCategoryAndTitle(Long categoryId,String title) {
        return productRepository.findAllByCategoryIdAndNameContaining(categoryId, title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            productRepository.deleteById(productId);
            return;
        }
        throw new IllegalArgumentException("Product with Id: "+ productId+" not found");
    }

    @Override
    public ProductDto getProductById(Long productId) {
       Optional<Product> optionalProduct = productRepository.findById(productId);
       return optionalProduct.map(Product::getProductDto).orElse(null);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setName(product.getName());
            product.setDescription(productDto.getDescription());
            product.setPrice(productDto.getPrice());

            if(productDto.getImg() != null){
                product.setImg(productDto.getImg().getBytes());
            }
            Product updatedProduct = productRepository.save(product);
            ProductDto updatedProductDto = new ProductDto();
            updatedProductDto.setId(updatedProduct.getId());
            return updatedProductDto;
        }
        return null;
    }
}

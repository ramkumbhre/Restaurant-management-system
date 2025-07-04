package com.Restaurant.management.system.services.admin;

import com.Restaurant.management.system.dtos.CategoryDto;

import java.io.IOException;

public interface AdminService {

   CategoryDto postCategory(CategoryDto categoryDto) throws IOException;
}


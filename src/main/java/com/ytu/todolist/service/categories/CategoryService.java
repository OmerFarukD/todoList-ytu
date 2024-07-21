package com.ytu.todolist.service.categories;

import com.ytu.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.ytu.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.ytu.todolist.dtos.responses.categories.CategoryResponseDto;
import com.ytu.todolist.entities.Category;

import java.util.List;

public sealed interface CategoryService permits CategoryManager {


    CategoryResponseDto getById(Long id);
    String delete(Long id);
    List<CategoryResponseDto> getAllCategories();

    String add(CategoryAddRequestDto dto);

    String update(CategoryUpdateRequestDto dto);
}

package com.ytu.todolist.service.categories;

import com.ytu.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.ytu.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.ytu.todolist.dtos.responses.categories.CategoryResponseDto;
import com.ytu.todolist.entities.Category;

public sealed interface BaseCategoryMapper  permits  CategoryMapper, CategoryModelMapper{
    CategoryResponseDto convertToDto(Category category);
    Category convertToEntity(CategoryUpdateRequestDto dto);
    Category convertToEntity(CategoryAddRequestDto dto);
}

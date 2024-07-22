package com.ytu.todolist.service.categories;

import com.ytu.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.ytu.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.ytu.todolist.dtos.responses.categories.CategoryResponseDto;
import com.ytu.todolist.entities.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component("Otomatik")
public final class CategoryModelMapper implements  BaseCategoryMapper{

    private final ModelMapper modelMapper;

    public CategoryModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponseDto convertToDto(Category category) {
        return modelMapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public Category convertToEntity(CategoryUpdateRequestDto dto) {
        return modelMapper.map(dto, Category.class);
    }

    @Override
    public Category convertToEntity(CategoryAddRequestDto dto) {
        return modelMapper.map(dto,Category.class);
    }
}

package com.ytu.todolist.dtos.requests.categories;

import com.ytu.todolist.aop.annotations.CategoryNameMustBeUnique;

public record CategoryAddRequestDto(

        @CategoryNameMustBeUnique
        String name,

        String description) {
}

package com.ytu.todolist.api;


import com.ytu.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.ytu.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.ytu.todolist.dtos.responses.categories.CategoryResponseDto;
import com.ytu.todolist.entities.Category;
import com.ytu.todolist.service.categories.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories/")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("add")
    public ResponseEntity<String> add(@Valid @RequestBody CategoryAddRequestDto categoryAddRequestDto){
        var result = categoryService.add(categoryAddRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("update")
    public  ResponseEntity<String> update(@Valid @RequestBody CategoryUpdateRequestDto updateRequestDto){

        var result = categoryService.update(updateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("getbyid")
    public ResponseEntity<CategoryResponseDto> getById(@RequestParam long id){
        var result = categoryService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("getall")
    public ResponseEntity<List<CategoryResponseDto>> getall(){
        var result = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}

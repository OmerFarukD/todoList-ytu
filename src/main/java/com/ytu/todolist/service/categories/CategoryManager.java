package com.ytu.todolist.service.categories;

import com.ytu.todolist.dataAccess.CategoryRepository;
import com.ytu.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.ytu.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.ytu.todolist.dtos.responses.categories.CategoryResponseDto;
import com.ytu.todolist.entities.Category;
import com.ytu.todolist.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public final class CategoryManager implements  CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public CategoryResponseDto getById(Long id) {

        Category category = this.categoryRepository.findById(id).orElseThrow(()->new NotFoundException(id,"Kategori"));

        CategoryResponseDto response = convertToDto(category);

        return response;

    }

    @Override
    public String delete(Long id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(()->new NotFoundException(id,"Kategori"));
        this.categoryRepository.delete(category);

        return "Kategori silindi.";
    }

    @Override
    public List<CategoryResponseDto> getAllCategories()
    {
        //1. Yöntem (Geleneksel Yontem)

 /*       List<CategoryResponseDto> responseDtos = new ArrayList<>();

        List<Category> list = this.categoryRepository.findAll();

        for (Category category : list){
            CategoryResponseDto categoryResponseDto = convertToDto(category);
            responseDtos.add(categoryResponseDto);
        }

        return responseDtos;*/
        // todo: method reference ve lambda fonksiyonlarını araştırınız.
        return  this.categoryRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public String add(CategoryAddRequestDto dto) {

        Category category = convertToEntity(dto);
        this.categoryRepository.save(category);

        return "Kategori eklendi.";
    }

    @Override
    public String update(CategoryUpdateRequestDto dto) {

        //1. Yöntem
       boolean isPresent = this.categoryRepository.existsById(dto.id());

       if (isPresent){

           Category category = convertToEntity(dto);
           this.categoryRepository.save(category);
           return "Kategori güncellendi.";
       }
       else {
           throw  new NotFoundException(dto.id(), "Kategori");
       }

/*       Category category = this.categoryRepository.findById(dto.id()).orElseThrow(()->new NotFoundException(dto.id(), "Kategori"));

       category.setName(dto.name());
       category.setDescription(dto.description());

       this.categoryRepository.save(category);
        return "Kategori güncellendi.";*/
    }

    // static polyöorphism (OverLoading)
    private Category convertToEntity(CategoryAddRequestDto dto){

        Category category = new Category();
        category.setName(dto.name());
        category.setDescription(dto.description());

        return  category;
    }
    private Category convertToEntity(CategoryUpdateRequestDto dto){

        Category category = new Category();
        category.setId(dto.id());
        category.setName(dto.name());
        category.setDescription(dto.description());

        return  category;
    }

    private CategoryResponseDto convertToDto(Category category){
        return  new CategoryResponseDto(category.getId(), category.getName(), category.getDescription());
    }




}

package com.ytu.todolist.service.categories;

import com.ytu.todolist.dataAccess.CategoryRepository;
import com.ytu.todolist.dtos.requests.categories.CategoryAddRequestDto;
import com.ytu.todolist.dtos.requests.categories.CategoryUpdateRequestDto;
import com.ytu.todolist.dtos.responses.categories.CategoryResponseDto;
import com.ytu.todolist.entities.Category;
import com.ytu.todolist.exceptions.BusinessException;
import com.ytu.todolist.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

// SOLID

@Service
public final class CategoryManager implements  CategoryService {

    private final CategoryRepository categoryRepository;
    private final BaseCategoryMapper mapper;

    //@Qualifier
    public CategoryManager(CategoryRepository categoryRepository, @Qualifier("Manuel") BaseCategoryMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }


    @Override
    public CategoryResponseDto getById(Long id) {

        Category category = this.categoryRepository.findById(id).orElseThrow(()->new NotFoundException(id,"Kategori"));

        CategoryResponseDto response = mapper.convertToDto(category);

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
                .map(mapper::convertToDto)
                .toList();
    }


    // todo: Kategori eklenirken aynı isimde kategori varsa eklemesin.
    @Override
    public String add(CategoryAddRequestDto dto) {


        Category category =mapper.convertToEntity(dto);
        this.categoryRepository.save(category);

        return "Kategori eklendi.";
    }

    @Override
    public String update(CategoryUpdateRequestDto dto) {

        //1. Yöntem
       boolean isPresent = this.categoryRepository.existsById(dto.id());

       if (isPresent){

           Category category = mapper.convertToEntity(dto);
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


    private  void categoryNameMustBeUnique(String name){
        int count = this.categoryRepository.countByName(name);
        if (count>0){
            throw new BusinessException("Bu isimde bir kategori zaten var :" +name);
        }
    }
}

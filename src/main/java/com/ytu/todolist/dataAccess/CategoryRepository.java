package com.ytu.todolist.dataAccess;

import com.ytu.todolist.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category,Long> {

}

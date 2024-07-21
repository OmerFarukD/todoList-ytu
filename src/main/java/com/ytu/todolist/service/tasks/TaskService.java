package com.ytu.todolist.service.tasks;

import com.ytu.todolist.dtos.requests.tasks.TaskAddRequestDto;
import com.ytu.todolist.dtos.responses.tasks.TaskDetailResponseDto;
import com.ytu.todolist.dtos.responses.tasks.TaskResponseDto;
import com.ytu.todolist.entities.Task;
import com.ytu.todolist.exceptions.NotFoundException;

import java.util.List;

public sealed interface TaskService  permits TaskManager{


    // İşlem başarılı mı  success
    // Mesaj
    // Data

    List<TaskResponseDto> getAllByCategoryName(String categoryName);

    String add(TaskAddRequestDto dto);
    TaskResponseDto getById(Long id) throws NotFoundException;

    List<TaskResponseDto> getAll();

    void delete(Long id);
    String  update(Task task);

    List<TaskDetailResponseDto> getAllDetails();

}

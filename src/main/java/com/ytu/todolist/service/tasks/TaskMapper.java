package com.ytu.todolist.service.tasks;

import com.ytu.todolist.dtos.requests.tasks.TaskAddRequestDto;
import com.ytu.todolist.dtos.responses.tasks.TaskDetailResponseDto;
import com.ytu.todolist.dtos.responses.tasks.TaskResponseDto;
import com.ytu.todolist.entities.Category;
import com.ytu.todolist.entities.Task;
import com.ytu.todolist.entities.enums.MissionStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskMapper {

    protected List<TaskDetailResponseDto> convertToDetailDtoList(List<Task> tasks){
        List<TaskDetailResponseDto> responseDtos = new ArrayList<>();
        for (Task task : tasks){
            TaskDetailResponseDto dto = convertToDetailDto(task);
            responseDtos.add(dto);
        }
        return responseDtos;
    }


    protected TaskDetailResponseDto convertToDetailDto(Task task){

        return  new TaskDetailResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCategory().getName(),
                task.getCategory().getDescription(),
                task.getStartDate(),
                task.getEndDate(),
                task.getPriority(),
                task.getMissionStatus()
        );
    }

    protected   List<TaskResponseDto> convertToDtoList(List<Task> tasks){
        List<TaskResponseDto> responseDtos = new ArrayList<>();
        for (Task task : tasks){
            TaskResponseDto responseDto = convertToDto(task);
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }

    protected TaskResponseDto convertToDto(Task task){

        return  new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStartDate(),
                task.getEndDate(),
                task.getCreateDate(),
                task.getPriority(),
                task.getMissionStatus()
        );
    }

    protected Task convertToEntity(TaskAddRequestDto dto){
        Task task = new Task();

        Category category = new Category();
        category.setId(dto.categoryId());


        task.setCategory(category);
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStartDate(dto.startDate());
        task.setEndDate(dto.endDate());
        task.setPriority(dto.priority());
        task.setCreateDate(new Date());
        task.setMissionStatus(MissionStatus.IN_PROCESS);

        return task;

    }
}

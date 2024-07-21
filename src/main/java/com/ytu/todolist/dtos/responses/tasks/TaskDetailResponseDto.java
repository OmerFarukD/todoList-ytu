package com.ytu.todolist.dtos.responses.tasks;

import com.ytu.todolist.entities.enums.MissionStatus;
import com.ytu.todolist.entities.enums.Priority;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class TaskDetailResponseDto{
    Long id;
    String  title;
    String description;

    String categoryName;
    String categoryDescription;
    Date startDate;
    Date endDate;
    Priority priority;
    MissionStatus missionStatus;

    public TaskDetailResponseDto(Long id, String title, String description, String categoryName, String categoryDescription, Date startDate, Date endDate, Priority priority, MissionStatus missionStatus) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.missionStatus = missionStatus;
    }
}






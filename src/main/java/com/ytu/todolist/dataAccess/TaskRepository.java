package com.ytu.todolist.dataAccess;

import com.ytu.todolist.dtos.responses.tasks.TaskDetailResponseDto;
import com.ytu.todolist.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findAllByCategoryName(String categoryName);
    @Query("SELECT new com.ytu.todolist.dtos.responses.tasks.TaskDetailResponseDto(t.id, t.title, t.description, c.name, c.description,t.startDate,t.endDate,t.priority,t.missionStatus)" +
            " from Task t inner join t.category c ")
    List<TaskDetailResponseDto> getDetails();

}

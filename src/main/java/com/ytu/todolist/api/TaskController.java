package com.ytu.todolist.api;
import com.ytu.todolist.dtos.requests.tasks.TaskAddRequestDto;
import com.ytu.todolist.dtos.responses.tasks.TaskDetailResponseDto;
import com.ytu.todolist.dtos.responses.tasks.TaskResponseDto;
import com.ytu.todolist.service.tasks.TaskService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/tasks/")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("add")
    public ResponseEntity<String> add(@RequestBody @Valid TaskAddRequestDto dto){

        String  result = taskService.add(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("getbyid/{id}")
    public ResponseEntity<TaskResponseDto> getById(@PathVariable("id") Long id) {
        TaskResponseDto result = taskService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("getall")
    public ResponseEntity<List<TaskResponseDto>> getAll(){
        List<TaskResponseDto> tasks = taskService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

    @GetMapping("getallbycategoryname")
    public ResponseEntity<List<TaskResponseDto>> getAllByCategory(@RequestParam String categoryName){
        List<TaskResponseDto> tasks = taskService.getAllByCategoryName(categoryName);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }


    @GetMapping("getdetails")
    public ResponseEntity<List<TaskDetailResponseDto>> getDetails(){
        var result = this.taskService.getAllDetails();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


/*    @GetMapping("getallcategoryname")
    public ResponseEntity<List<TaskDetailResponseDto>> getAllCategoryName(@RequestParam String  category){
        var result = this.taskService.getAllCategoryName(category);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }*/
    @GetMapping("getallbycategoryid")
    public ResponseEntity<List<TaskDetailResponseDto>> getAllByCategoryId(@RequestParam Long categoryId){
        List<TaskDetailResponseDto> tasks = taskService.getAllCategoryId(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }


    @GetMapping("getstartandfinishrange")
    public ResponseEntity<List<TaskDetailResponseDto>> getAllDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end

    ){
        List<TaskDetailResponseDto> tasks = taskService.getAllDateRange(start,end);
        return ResponseEntity.status(HttpStatus.OK).body(tasks);
    }

}

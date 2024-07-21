package com.ytu.todolist.service.tasks;
import com.ytu.todolist.dataAccess.TaskRepository;
import com.ytu.todolist.dtos.requests.tasks.TaskAddRequestDto;
import com.ytu.todolist.dtos.responses.tasks.TaskDetailResponseDto;
import com.ytu.todolist.dtos.responses.tasks.TaskResponseDto;
import com.ytu.todolist.entities.Category;
import com.ytu.todolist.entities.Task;
import com.ytu.todolist.entities.enums.MissionStatus;
import com.ytu.todolist.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public final class TaskManager implements  TaskService{

    private final TaskRepository taskRepository;

    public TaskManager(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskResponseDto> getAllByCategoryName(String categoryName) {
        List<Task> tasks = this.taskRepository.findAllByCategoryName(categoryName);

        return  convertToDtoList(tasks);
    }

    @Override
    public String add(TaskAddRequestDto dto) {
        Task task = convertToEntity(dto);
        this.taskRepository.save(task);
        return "Görev kaydedildi";
    }
    @Override
    public TaskResponseDto getById(Long id) {

        Task task = taskRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"Görev"));

        return convertToDto(task);
    }

    @Override
    public List<TaskResponseDto> getAll()
    {
      List<Task> tasks =   this.taskRepository.findAll();

      return convertToDtoList(tasks);
    }

    @Override
    public void delete(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(()-> new NotFoundException(id,"Görev"));
        this.taskRepository.delete(task);
    }

    @Override
    public String update(Task task) {
        taskRepository.save(task);
        return "Görev güncellendi.";
    }

    @Override
    public List<TaskDetailResponseDto> getAllDetails() {
        List<Task> tasks = this.taskRepository.findAll();
        return convertToDetailDtoList(tasks);
    }


    private Task convertToEntity(TaskAddRequestDto dto){
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

    private TaskResponseDto convertToDto(Task task){

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


    private  List<TaskResponseDto> convertToDtoList(List<Task> tasks){
        List<TaskResponseDto> responseDtos = new ArrayList<>();
        for (Task task : tasks){
            TaskResponseDto responseDto = convertToDto(task);
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }


    private TaskDetailResponseDto convertToDetailDto(Task task){

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

    private  List<TaskDetailResponseDto> convertToDetailDtoList(List<Task> tasks){
        List<TaskDetailResponseDto> responseDtos = new ArrayList<>();
        for (Task task : tasks){
            TaskDetailResponseDto dto = convertToDetailDto(task);
            responseDtos.add(dto);
        }
        return responseDtos;
    }
}

package com.venhancer.spring_boot_todo_application.service.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.venhancer.spring_boot_todo_application.dto.TaskDto;
import com.venhancer.spring_boot_todo_application.entity.Task;
import com.venhancer.spring_boot_todo_application.exception.ResourceNotFoundException;
import com.venhancer.spring_boot_todo_application.mapper.TaskMapper;
import com.venhancer.spring_boot_todo_application.repository.TaskRepository;
import com.venhancer.spring_boot_todo_application.service.TaskService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TaskServiceImp implements TaskService{

    private TaskRepository taskRepository;

    @Override
    public TaskDto createTask(TaskDto taskDto) {

        Task task = TaskMapper.INSTANCE.mapToTask(taskDto);
        Task savedTask = taskRepository.save(task);


        return TaskMapper.INSTANCE.mapToTaskDto(savedTask);
    }

    @Override
    public TaskDto getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task is not exist with the given id: " + taskId));
        return TaskMapper.INSTANCE.mapToTaskDto(task);
    }

    @Override
    public List<TaskDto> getAllTasks(String filterPriority) {
        List<Task> tasks;

        if (filterPriority == null || filterPriority.isEmpty()) {
            tasks = taskRepository.findAll();
            return tasks.stream().map((item) -> TaskMapper.INSTANCE.mapToTaskDto(item)).collect(Collectors.toList());
        }

        tasks = taskRepository.findByPriorityIgnoreCase(filterPriority);
        return tasks.stream().map((item) -> TaskMapper.INSTANCE.mapToTaskDto(item)).collect(Collectors.toList());
    }

    @Override
    public TaskDto updateTask(Long taskId, TaskDto updatedTask) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task is not exist with the given id: " + taskId));
        task.setTitle(updatedTask.getTitle());

        if (updatedTask.isCompleted()){
            task.setCompleted(updatedTask.isCompleted());
            task.setCompletedDate(LocalDateTime.now());
        } else {
            if (task.getCompletedDate() != null) {
                task.setCompletedDate(null);
            }
            task.setCompleted(updatedTask.isCompleted());
            task.setLastModifiedDate(LocalDateTime.now());
        }

        Task updatedTaskObject = taskRepository.save(task);
        return TaskMapper.INSTANCE.mapToTaskDto(updatedTaskObject);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("Task is not exist with the given id: " + taskId));
        taskRepository.deleteById(taskId);
    }

    @Override
    public List<TaskDto> getAllTasksByTitle(String taskTitle) {
        List<Task> tasks = taskRepository.findByTitle(taskTitle);
        return tasks.stream().map((item) -> TaskMapper.INSTANCE.mapToTaskDto(item)).collect(Collectors.toList());
    }
}
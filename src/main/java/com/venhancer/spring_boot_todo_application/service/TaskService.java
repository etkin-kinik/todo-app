package com.venhancer.spring_boot_todo_application.service;

import java.util.List;

import com.venhancer.spring_boot_todo_application.dto.TaskDto;

public interface TaskService {
    TaskDto createTask(TaskDto taskDto);
    TaskDto getTaskById(Long taskId);
    List<TaskDto> getAllTasks(String filterPriority);
    TaskDto updateTask(Long taskId, TaskDto taskDto);
    void deleteTask(Long taskId);
    List<TaskDto> getAllTasksByTitle(String taskTitle);
}
package com.venhancer.spring_boot_todo_application.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.venhancer.spring_boot_todo_application.dto.TaskDto;
import com.venhancer.spring_boot_todo_application.service.TaskService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        TaskDto createdTask = taskService.createTask(taskDto);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long taskId){
        TaskDto getTask = taskService.getTaskById(taskId);
        return ResponseEntity.ok(getTask);
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<TaskDto>> getAllTasksByTitle(@PathVariable("title") String taskTitle){
        List<TaskDto> allTasksByTitle = taskService.getAllTasksByTitle(taskTitle);
        return ResponseEntity.ok(allTasksByTitle);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam(required = false, name = "filterPriority") String filterPriority){
        List<TaskDto> allTasks = taskService.getAllTasks(filterPriority);
        return ResponseEntity.ok(allTasks);
    }
    
    @PutMapping("{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long taskId, @RequestBody TaskDto updatedTask){
        TaskDto task = taskService.updateTask(taskId, updatedTask);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long taskId){
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task successfully deleted!");
    }
}
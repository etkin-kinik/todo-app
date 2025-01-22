package com.venhancer.spring_boot_todo_application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.venhancer.spring_boot_todo_application.dto.TaskDto;
import com.venhancer.spring_boot_todo_application.entity.Task;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto mapToTaskDto(Task task);
    Task mapToTask(TaskDto taskDto);

}
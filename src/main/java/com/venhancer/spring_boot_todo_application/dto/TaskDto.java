package com.venhancer.spring_boot_todo_application.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    private Long id;
    private String title;
    private boolean completed = Boolean.FALSE;
    private String priority = "Low";
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime completedDate;
}
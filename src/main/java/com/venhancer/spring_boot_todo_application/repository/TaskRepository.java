package com.venhancer.spring_boot_todo_application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.venhancer.spring_boot_todo_application.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    List<Task> findByPriorityIgnoreCase(String priority);

    // @Query("SELECT t FROM Task t WHERE t.title ILIKE %:title%")
    // List<Task> findByTitle(@Param("title") String title);

    @Query(value = "SELECT * FROM tasks WHERE title ILIKE CONCAT('%', :title, '%')", nativeQuery = true)
    List<Task> findByTitle(@Param("title") String title);

}
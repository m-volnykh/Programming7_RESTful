package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Найти все задачи по статусу
//    List<Task> findByStatus(String status);

    // Найти задачи, содержащие слово в заголовке
//    List<Task> findByTitleContainingIgnoreCase(String keyword);

    // Комбинированный поиск: по статусу и заголовку
//    List<Task> findByStatusAndTitleContainingIgnoreCase(String status, String keyword);
}

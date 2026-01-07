package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // --- Получить все задачи ---
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // --- Получить задачу по id ---
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // --- Создать новую задачу ---
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    // --- Обновить существующую задачу ---
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (!optionalTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Task task = optionalTask.get();
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());

        Task updatedTask = taskRepository.save(task);
        return ResponseEntity.ok(updatedTask);
    }

    // --- Удалить задачу ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (!task.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        taskRepository.delete(task.get());
        return ResponseEntity.noContent().build();
    }

    // // --- Поиск по статусу ---
    // @GetMapping("/search/status")
    // public List<Task> getByStatus(@RequestParam String status) {
    //     return taskRepository.findByStatus(status);
    // }

    // // --- Поиск по ключевому слову в заголовке ---
    // @GetMapping("/search/title")
    // public List<Task> getByTitle(@RequestParam String keyword) {
    //     return taskRepository.findByTitleContainingIgnoreCase(keyword);
    // }

    // // --- Комбинированный поиск ---
    // @GetMapping("/search")
    // public List<Task> search(
    //         @RequestParam(required = false) String status,
    //         @RequestParam(required = false) String keyword) {

    //     if (status != null && keyword != null) {
    //         return taskRepository.findByStatusAndTitleContainingIgnoreCase(status, keyword);
    //     } else if (status != null) {
    //         return taskRepository.findByStatus(status);
    //     } else if (keyword != null) {
    //         return taskRepository.findByTitleContainingIgnoreCase(keyword);
    //     } else {
    //         return taskRepository.findAll();
    //     }
    // }
}

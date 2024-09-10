package ru.egartech.employeemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.egartech.employeemanagementsystem.dto.TaskDto;
import ru.egartech.employeemanagementsystem.model.Task;
import ru.egartech.employeemanagementsystem.service.impl.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks().stream()
                .map(taskService::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        return taskService.convertToDto(task);
    }

    @PostMapping
    public TaskDto createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return taskService.convertToDto(createdTask);
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return taskService.convertToDto(updatedTask);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}

package ru.egartech.employeemanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.egartech.employeemanagementsystem.dto.TaskDto;
import ru.egartech.employeemanagementsystem.model.Task;
import ru.egartech.employeemanagementsystem.service.impl.TaskService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task Controller", description = "API для работы с задачами")
public class TaskController {

  private final TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @Operation(summary = "Получить все задачи")
  @GetMapping
  public List<TaskDto> getAllTasks() {
    return taskService.getAllTasks().stream()
        .map(taskService::convertToDto)
        .collect(Collectors.toList());
  }

  @Operation(summary = "Получить задачу по ID")
  @GetMapping("/{id}")
  public TaskDto getTaskById(@PathVariable Long id) {
    Task task = taskService.getTaskById(id)
        .orElseThrow(() -> new RuntimeException("Задача не найдена"));
    return taskService.convertToDto(task);
  }

  @Operation(summary = "Создать новую задачу")
  @PostMapping
  public TaskDto createTask(@RequestBody Task task) {
    Task createdTask = taskService.createTask(task);
    return taskService.convertToDto(createdTask);
  }

  @Operation(summary = "Обновить задачу по ID")
  @PutMapping("/{id}")
  public TaskDto updateTask(@PathVariable Long id, @RequestBody Task task) {
    Task updatedTask = taskService.updateTask(id, task);
    return taskService.convertToDto(updatedTask);
  }

  @Operation(summary = "Удалить задачу по ID")
  @DeleteMapping("/{id}")
  public void deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
  }
}
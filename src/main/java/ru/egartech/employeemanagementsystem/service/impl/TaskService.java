package ru.egartech.employeemanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.employeemanagementsystem.dto.TaskDto;
import ru.egartech.employeemanagementsystem.mapper.TaskMapper;
import ru.egartech.employeemanagementsystem.model.Task;
import ru.egartech.employeemanagementsystem.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

  private final TaskRepository taskRepository;
  private final TaskMapper taskMapper;

  @Autowired
  public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
    this.taskRepository = taskRepository;
    this.taskMapper = taskMapper;
  }

  public TaskDto createTask(TaskDto taskDto) {
    Task task = taskMapper.toEntity(taskDto);
    Task savedTask = taskRepository.save(task);
    return taskMapper.toDto(savedTask);
  }

  public TaskDto getTask(Long id) {
    return taskRepository.findById(id)
        .map(taskMapper::toDto)
        .orElseThrow(() -> new RuntimeException("Задача не найдена"));
  }

  public List<TaskDto> getAllTasks() {
    return taskRepository.findAll()
        .stream()
        .map(taskMapper::toDto)
        .collect(Collectors.toList());
  }

  public TaskDto updateTask(Long id, TaskDto taskDto) {
    Task existingTask = taskRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Задача не найдена"));
    Task updatedTask = taskMapper.toEntity(taskDto);
    updatedTask.setId(existingTask.getId());
    Task savedTask = taskRepository.save(updatedTask);
    return taskMapper.toDto(savedTask);
  }

  public void deleteTask(Long id) {
    taskRepository.deleteById(id);
  }
}
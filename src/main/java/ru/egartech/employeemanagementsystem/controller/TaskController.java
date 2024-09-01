package ru.egartech.employeemanagementsystem.controller;

import ru.egartech.employeemanagementsystem.model.Task;
import ru.egartech.employeemanagementsystem.repository.TaskRepository;
import ru.egartech.employeemanagementsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public List<Task> getTasksByEmployee(@PathVariable Long employeeId) {
        return taskRepository.findByEmployeeId(employeeId);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        if (employeeRepository.existsById(task.getEmployee().getId())) {
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Employee not found");
        }
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        Optional<Task> existingTaskOpt = taskRepository.findById(taskId);
        if (existingTaskOpt.isPresent()) {
            Task existingTask = existingTaskOpt.get();

            // Обновляем поля задачи
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setEmployee(updatedTask.getEmployee());
            existingTask.setStatus(updatedTask.getStatus());

            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Task not found");
        }
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
        } else {
            throw new RuntimeException("Task not found");
        }
    }
}

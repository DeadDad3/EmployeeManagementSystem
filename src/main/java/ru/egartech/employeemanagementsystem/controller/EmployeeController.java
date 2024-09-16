package ru.egartech.employeemanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.egartech.employeemanagementsystem.dto.EmployeeDto;
import ru.egartech.employeemanagementsystem.model.Employee;
import ru.egartech.employeemanagementsystem.service.impl.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Controller", description = "API для работы с сотрудниками")
public class EmployeeController {

  private final EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @Operation(summary = "Получить всех сотрудников")
  @GetMapping
  public List<EmployeeDto> getAllEmployees() {
    return employeeService.getAllEmployees().stream()
        .map(employeeService::convertToDto)
        .collect(Collectors.toList());
  }

  @Operation(summary = "Получить сотрудника по ID")
  @GetMapping("/{id}")
  public EmployeeDto getEmployeeById(@PathVariable Long id) {
    Employee employee = employeeService.getEmployeeById(id)
        .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
    return employeeService.convertToDto(employee);
  }

  @Operation(summary = "Создать нового сотрудника")
  @PostMapping
  public EmployeeDto createEmployee(@RequestBody Employee employee) {
    Employee createdEmployee = employeeService.createEmployee(employee);
    return employeeService.convertToDto(createdEmployee);
  }

  @Operation(summary = "Обновить сотрудника по ID")
  @PutMapping("/{id}")
  public EmployeeDto updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
    Employee updatedEmployee = employeeService.updateEmployee(id, employee);
    return employeeService.convertToDto(updatedEmployee);
  }

  @Operation(summary = "Удалить сотрудника по ID")
  @DeleteMapping("/{id}")
  public void deleteEmployee(@PathVariable Long id) {
    employeeService.deleteEmployee(id);
  }
}
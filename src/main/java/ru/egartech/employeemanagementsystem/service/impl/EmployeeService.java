package ru.egartech.employeemanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.employeemanagementsystem.dto.EmployeeDto;
import ru.egartech.employeemanagementsystem.mapper.EmployeeMapper;
import ru.egartech.employeemanagementsystem.model.Employee;
import ru.egartech.employeemanagementsystem.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper employeeMapper;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
    this.employeeRepository = employeeRepository;
    this.employeeMapper = employeeMapper;
  }

  public EmployeeDto createEmployee(EmployeeDto employeeDto) {
    Employee employee = employeeMapper.toEntity(employeeDto);
    Employee savedEmployee = employeeRepository.save(employee);
    return employeeMapper.toDto(savedEmployee);
  }

  public EmployeeDto getEmployee(Long id) {
    return employeeRepository.findById(id)
        .map(employeeMapper::toDto)
        .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));
  }

  public List<EmployeeDto> getAllEmployees() {
    return employeeRepository.findAll()
        .stream()
        .map(employeeMapper::toDto)
        .collect(Collectors.toList());
  }

  public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
    Employee existingEmployee = employeeRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Сотрдник не найден"));
    Employee updatedEmployee = employeeMapper.toEntity(employeeDto);
    updatedEmployee.setId(existingEmployee.getId());
    Employee savedEmployee = employeeRepository.save(updatedEmployee);
    return employeeMapper.toDto(savedEmployee);
  }

  public void deleteEmployee(Long id) {
    employeeRepository.deleteById(id);
  }
}
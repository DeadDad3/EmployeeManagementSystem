package ru.egartech.employeemanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.egartech.employeemanagementsystem.dto.EmployeeDto;
import ru.egartech.employeemanagementsystem.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
  EmployeeDto toDto(Employee employee);
  Employee toEntity(EmployeeDto employeeDto);
}
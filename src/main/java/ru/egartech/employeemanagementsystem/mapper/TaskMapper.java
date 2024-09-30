package ru.egartech.employeemanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.egartech.employeemanagementsystem.dto.TaskDto;
import ru.egartech.employeemanagementsystem.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
  TaskDto toDto(Task task);
  Task toEntity(TaskDto taskDto);
}
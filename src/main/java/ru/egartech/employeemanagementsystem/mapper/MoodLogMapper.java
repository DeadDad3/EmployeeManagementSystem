package ru.egartech.employeemanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.egartech.employeemanagementsystem.dto.MoodLogDto;
import ru.egartech.employeemanagementsystem.model.MoodLog;

@Mapper(componentModel = "spring")
public interface MoodLogMapper {
  MoodLogDto toDto(MoodLog moodLog);
  MoodLog toEntity(MoodLogDto moodLogDto);
}
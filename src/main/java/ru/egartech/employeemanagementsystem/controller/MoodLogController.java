package ru.egartech.employeemanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.egartech.employeemanagementsystem.dto.MoodLogDto;
import ru.egartech.employeemanagementsystem.model.MoodLog;
import ru.egartech.employeemanagementsystem.service.impl.MoodLogService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/moodlog")
@Tag(name = "MoodLog Controller", description = "API для работы с логами настроений")
public class MoodLogController {

  private final MoodLogService moodLogService;

  @Autowired
  public MoodLogController(MoodLogService moodLogService) {
    this.moodLogService = moodLogService;
  }

  @Operation(summary = "Получить все логи настроений")
  @GetMapping
  public List<MoodLogDto> getAllMoodLogs() {
    return moodLogService.getAllMoodLogs().stream()
        .map(moodLogService::convertToDto)
        .collect(Collectors.toList());
  }

  @Operation(summary = "Получить лог настроения по ID")
  @GetMapping("/{id}")
  public MoodLogDto getMoodLogById(@PathVariable Long id) {
    MoodLog moodLog = moodLogService.getMoodLogById(id)
        .orElseThrow(() -> new RuntimeException("Лог настроения не найден"));
    return moodLogService.convertToDto(moodLog);
  }

  @Operation(summary = "Создать лог настроения")
  @PostMapping
  public MoodLogDto createMoodLog(@RequestBody MoodLog moodLog) {
    MoodLog createdMoodLog = moodLogService.createMoodLog(moodLog);
    return moodLogService.convertToDto(createdMoodLog);
  }

  @Operation(summary = "Обновить лог настроения по ID")
  @PutMapping("/{id}")
  public MoodLogDto updateMoodLog(@PathVariable Long id, @RequestBody MoodLog moodLog) {
    MoodLog updatedMoodLog = moodLogService.updateMoodLog(id, moodLog);
    return moodLogService.convertToDto(updatedMoodLog);
  }

  @Operation(summary = "Удалить лог настроения по ID")
  @DeleteMapping("/{id}")
  public void deleteMoodLog(@PathVariable Long id) {
    moodLogService.deleteMoodLog(id);
  }
}
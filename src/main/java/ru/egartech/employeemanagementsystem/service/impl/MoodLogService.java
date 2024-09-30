package ru.egartech.employeemanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.employeemanagementsystem.dto.MoodLogDto;
import ru.egartech.employeemanagementsystem.mapper.MoodLogMapper;
import ru.egartech.employeemanagementsystem.model.MoodLog;
import ru.egartech.employeemanagementsystem.repository.MoodLogRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoodLogService {

  private final MoodLogRepository moodLogRepository;
  private final MoodLogMapper moodLogMapper;

  @Autowired
  public MoodLogService(MoodLogRepository moodLogRepository, MoodLogMapper moodLogMapper) {
    this.moodLogRepository = moodLogRepository;
    this.moodLogMapper = moodLogMapper;
  }

  public MoodLogDto createMoodLog(MoodLogDto moodLogDto) {
    MoodLog moodLog = moodLogMapper.toEntity(moodLogDto);
    MoodLog savedMoodLog = moodLogRepository.save(moodLog);
    return moodLogMapper.toDto(savedMoodLog);
  }

  public MoodLogDto getMoodLog(Long id) {
    return moodLogRepository.findById(id)
        .map(moodLogMapper::toDto)
        .orElseThrow(() -> new RuntimeException("Настроение не найдено"));
  }

  public List<MoodLogDto> getAllMoodLogs() {
    return moodLogRepository.findAll()
        .stream()
        .map(moodLogMapper::toDto)
        .collect(Collectors.toList());
  }

  public MoodLogDto updateMoodLog(Long id, MoodLogDto moodLogDto) {
    MoodLog existingMoodLog = moodLogRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Настроение не найдено"));
    MoodLog updatedMoodLog = moodLogMapper.toEntity(moodLogDto);
    updatedMoodLog.setId(existingMoodLog.getId());
    MoodLog savedMoodLog = moodLogRepository.save(updatedMoodLog);
    return moodLogMapper.toDto(savedMoodLog);
  }

  public void deleteMoodLog(Long id) {
    moodLogRepository.deleteById(id);
  }
}

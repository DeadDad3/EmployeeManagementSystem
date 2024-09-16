package ru.egartech.employeemanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.employeemanagementsystem.dto.MoodLogDto;
import ru.egartech.employeemanagementsystem.model.MoodLog;
import ru.egartech.employeemanagementsystem.repository.MoodLogRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MoodLogService {

  private final MoodLogRepository moodLogRepository;

  @Autowired
  public MoodLogService(MoodLogRepository moodLogRepository) {
    this.moodLogRepository = moodLogRepository;
  }

  public MoodLogDto convertToDto(MoodLog moodLog) {
    MoodLogDto dto = new MoodLogDto();
    dto.setId(moodLog.getId());
    dto.setMood(moodLog.getMood());
    dto.setReason(moodLog.getReason());
    dto.setEmployeeId(moodLog.getEmployee().getId());
    dto.setEmployeeFirstName(moodLog.getEmployee().getFirstName());
    dto.setEmployeeLastName(moodLog.getEmployee().getLastName());
    return dto;
  }

  public List<MoodLog> getAllMoodLogs() {
    return moodLogRepository.findAll();
  }

  public Optional<MoodLog> getMoodLogById(Long id) {
    return moodLogRepository.findById(id);
  }

  public MoodLog createMoodLog(MoodLog moodLog) {
    return moodLogRepository.save(moodLog);
  }

  public MoodLog updateMoodLog(Long id, MoodLog updatedMoodLog) {
    return moodLogRepository.findById(id)
        .map(moodLog -> {
          moodLog.setMood(updatedMoodLog.getMood());
          moodLog.setReason(updatedMoodLog.getReason());
          return moodLogRepository.save(moodLog);
        })
        .orElseThrow(() -> new RuntimeException("Лог настроения не найден"));
  }

  public void deleteMoodLog(Long id) {
    moodLogRepository.deleteById(id);
  }
}
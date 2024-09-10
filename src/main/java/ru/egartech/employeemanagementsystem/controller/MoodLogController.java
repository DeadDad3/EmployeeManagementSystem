package ru.egartech.employeemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.egartech.employeemanagementsystem.dto.MoodLogDto;
import ru.egartech.employeemanagementsystem.model.MoodLog;
import ru.egartech.employeemanagementsystem.service.impl.MoodLogService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/moodlog")
public class MoodLogController {

    private final MoodLogService moodLogService;

    @Autowired
    public MoodLogController(MoodLogService moodLogService) {
        this.moodLogService = moodLogService;
    }

    @GetMapping
    public List<MoodLogDto> getAllMoodLogs() {
        return moodLogService.getAllMoodLogs().stream()
                .map(moodLogService::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MoodLogDto getMoodLogById(@PathVariable Long id) {
        MoodLog moodLog = moodLogService.getMoodLogById(id)
                .orElseThrow(() -> new RuntimeException("Лог настроения не найден"));
        return moodLogService.convertToDto(moodLog);
    }

    @PostMapping
    public MoodLogDto createMoodLog(@RequestBody MoodLog moodLog) {
        MoodLog createdMoodLog = moodLogService.createMoodLog(moodLog);
        return moodLogService.convertToDto(createdMoodLog);
    }

    @PutMapping("/{id}")
    public MoodLogDto updateMoodLog(@PathVariable Long id, @RequestBody MoodLog moodLog) {
        MoodLog updatedMoodLog = moodLogService.updateMoodLog(id, moodLog);
        return moodLogService.convertToDto(updatedMoodLog);
    }

    @DeleteMapping("/{id}")
    public void deleteMoodLog(@PathVariable Long id) {
        moodLogService.deleteMoodLog(id);
    }
}

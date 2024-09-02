package ru.egartech.employeemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.employeemanagementsystem.model.MoodLog;
import ru.egartech.employeemanagementsystem.repository.MoodLogRepository;

import java.util.List;

@RestController
@RequestMapping("/mood-logs")
public class MoodLogController {

    @Autowired
    private MoodLogRepository moodLogRepository;

    @GetMapping
    public List<MoodLog> getAllMoodLogs() {
        return moodLogRepository.findAll();
    }

    @PostMapping
    public MoodLog createMoodLog(@RequestBody MoodLog moodLog) {
        return moodLogRepository.save(moodLog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MoodLog> updateMoodLog(@PathVariable Long id, @RequestBody MoodLog moodLogDetails) {
        MoodLog moodLog = moodLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MoodLog not found with id " + id));
        moodLog.setMood(moodLogDetails.getMood());
        moodLog.setReason(moodLogDetails.getReason());
        moodLog.setDate(moodLogDetails.getDate());
        moodLog.setEmployee(moodLogDetails.getEmployee());
        final MoodLog updatedMoodLog = moodLogRepository.save(moodLog);
        return ResponseEntity.ok(updatedMoodLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMoodLog(@PathVariable Long id) {
        MoodLog moodLog = moodLogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MoodLog not found with id " + id));
        moodLogRepository.delete(moodLog);
        return ResponseEntity.noContent().build();
    }
}

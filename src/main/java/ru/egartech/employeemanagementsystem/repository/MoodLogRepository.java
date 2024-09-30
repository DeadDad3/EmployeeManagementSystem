package ru.egartech.employeemanagementsystem.repository;

import ru.egartech.employeemanagementsystem.model.MoodLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoodLogRepository extends JpaRepository<MoodLog, Long> {
}
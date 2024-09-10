package ru.egartech.employeemanagementsystem.model;

import jakarta.persistence.*;
import java.util.Date;

import lombok.Data;

@Entity
@Table(name = "mood_logs")
@Data
public class MoodLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "mood", nullable = false)
    private String mood;

    @Column(name = "reason")
    private String reason;

    public MoodLog() {
        // Устанавливаем текущую дату
        this.date = new Date();
    }
}

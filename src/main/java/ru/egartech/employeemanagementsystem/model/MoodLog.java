package ru.egartech.employeemanagementsystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mood_logs")
@Data
@Schema(description = "Модель для представления лога настроений")
public class MoodLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Идентификатор лога настроений", example = "501")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  @Schema(description = "Сотрудник, чей лог настроений сохраняется")
  private Employee employee;

  @Column(name = "mood", nullable = false)
  @Schema(description = "Настроение", example = "Хорошо")
  private String mood;

  @Column(name = "reason")
  @Schema(description = "Причина настроения", example = "Завершил важную задачу")
  private String reason;

  @Column(name = "date", nullable = false)
  @Schema(description = "Дата лога настроений")
  private String date;
}
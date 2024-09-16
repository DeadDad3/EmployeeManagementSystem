package ru.egartech.employeemanagementsystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
@Schema(description = "Модель для представления задачи")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Идентификатор задачи", example = "101")
  private Long id;

  @Column(name = "title", nullable = false)
  @Schema(description = "Название задачи", example = "Подготовить отчет")
  private String title;

  @Column(name = "description")
  @Schema(description = "Описание задачи", example = "Сделать еженедельный отчет по продажам")
  private String description;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  @Schema(description = "Сотрудник, выполняющий задачу")
  private Employee employee;

  @Column(name = "status")
  @Schema(description = "Статус задачи", example = "В процессе")
  private String status;
}
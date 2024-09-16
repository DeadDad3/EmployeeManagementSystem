package ru.egartech.employeemanagementsystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "notifications")
@Data
@Schema(description = "Модель для представления уведомления")
public class Notification {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Идентификатор уведомления", example = "1001")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "employee_id")
  @Schema(description = "Сотрудник, для которого предназначено уведомление")
  private Employee employee;

  @Column(name = "type")
  @Schema(description = "Тип уведомления", example = "Telegram")
  private String type;

  @Column(name = "content")
  @Schema(description = "Содержание уведомления", example = "Напоминание о задаче")
  private String content;

  @Column(name = "status")
  @Schema(description = "Статус уведомления", example = "Отправлено")
  private String status;
}
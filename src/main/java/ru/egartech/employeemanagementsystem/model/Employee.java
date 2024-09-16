package ru.egartech.employeemanagementsystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employees")
@Data
@Schema(description = "Модель для представления сотрудника")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Идентификатор сотрудника", example = "1")
  private Long id;

  @Column(name = "first_name", nullable = false)
  @Schema(description = "Имя сотрудника", example = "Иван")
  private String firstName;

  @Column(name = "last_name", nullable = false)
  @Schema(description = "Фамилия сотрудника", example = "Иванов")
  private String lastName;

  @Column(name = "email", unique = true, nullable = false)
  @Schema(description = "Email сотрудника", example = "ivan.ivanov@example.com")
  private String email;

  @Column(name = "position")
  @Schema(description = "Должность сотрудника", example = "Менеджер")
  private String position;

  @ManyToOne
  @JoinColumn(name = "manager_id")
  @Schema(description = "Руководитель сотрудника")
  private Employee manager;

  @Column(name = "chat_id")
  @Schema(description = "Chat ID сотрудника в Telegram", example = "856119181")
  private String chatId;

  @Column(name = "username")
  @Schema(description = "Никнейм сотрудника", example = "ivan_manager")
  private String username;
}
package ru.egartech.employeemanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO для представления лога настроений")
public class MoodLogDto {

  @Schema(description = "Идентификатор лога настроений", example = "501")
  private Long id;

  @Schema(description = "Настроение", example = "Отлично")
  private String mood;

  @Schema(description = "Причина настроения", example = "Завершил сложный проект")
  private String reason;

  @Schema(description = "Идентификатор сотрудника", example = "1")
  private Long employeeId;

  @Schema(description = "Имя сотрудника", example = "Иван")
  private String employeeFirstName;

  @Schema(description = "Фамилия сотрудника", example = "Иванов")

  private String employeeLastName;

  public MoodLogDto(Long id, String mood, String reason, Long employeeId, String employeeFirstName,
      String employeeLastName) {
    this.id = id;
    this.mood = mood;
    this.reason = reason;
    this.employeeId = employeeId;
    this.employeeFirstName = employeeFirstName;
    this.employeeLastName = employeeLastName;
  }

  public MoodLogDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMood() {
    return mood;
  }

  public void setMood(String mood) {
    this.mood = mood;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }

  public String getEmployeeFirstName() {
    return employeeFirstName;
  }

  public void setEmployeeFirstName(String employeeFirstName) {
    this.employeeFirstName = employeeFirstName;
  }

  public String getEmployeeLastName() {
    return employeeLastName;
  }

  public void setEmployeeLastName(String employeeLastName) {
    this.employeeLastName = employeeLastName;
  }
}
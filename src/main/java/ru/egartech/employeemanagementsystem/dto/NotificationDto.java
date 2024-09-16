package ru.egartech.employeemanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO для представления уведомления")
public class NotificationDto {

  @Schema(description = "Идентификатор уведомления", example = "1001")
  private Long id;

  @Schema(description = "Тип уведомления", example = "Telegram")
  private String type;

  @Schema(description = "Содержание уведомления", example = "Напоминание о задаче")
  private String content;

  @Schema(description = "Статус уведомления", example = "Отправлено")
  private String status;

  @Schema(description = "Идентификатор сотрудника", example = "1")
  private Long employeeId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Long getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Long employeeId) {
    this.employeeId = employeeId;
  }
}
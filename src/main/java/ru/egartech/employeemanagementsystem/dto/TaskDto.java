package ru.egartech.employeemanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO для представления задачи")
public class TaskDto {

  @Schema(description = "Идентификатор задачи", example = "101")
  private Long id;

  @Schema(description = "Название задачи", example = "Сделать отчет")
  private String title;

  @Schema(description = "Описание задачи", example = "Подготовить еженедельный отчет по продажам")
  private String description;

  @Schema(description = "Статус задачи", example = "В процессе")
  private String status;

  @Schema(description = "Идентификатор сотрудника", example = "1")
  private Long employeeId;

  @Schema(description = "Имя сотрудника", example = "Иван")
  private String employeeFirstName;

  @Schema(description = "Фамилия сотрудника", example = "Иванов")
  private String employeeLastName;

  public TaskDto() {
  }

  public TaskDto(Long id, String title, String description, String status, Long employeeId,
      String employeeFirstName, String employeeLastName) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.status = status;
    this.employeeId = employeeId;
    this.employeeFirstName = employeeFirstName;
    this.employeeLastName = employeeLastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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
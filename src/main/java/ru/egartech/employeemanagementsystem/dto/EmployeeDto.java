package ru.egartech.employeemanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO для представления сотрудника")
public class EmployeeDto {

  @Schema(description = "Идентификатор сотрудника", example = "1")
  private Long id;

  @Schema(description = "Имя сотрудника", example = "Иван")
  private String firstName;

  @Schema(description = "Фамилия сотрудника", example = "Иванов")
  private String lastName;

  @Schema(description = "Должность сотрудника", example = "Менеджер")
  private String position;

  @Schema(description = "Email сотрудника", example = "ivan.ivanov@example.com")
  private String email;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
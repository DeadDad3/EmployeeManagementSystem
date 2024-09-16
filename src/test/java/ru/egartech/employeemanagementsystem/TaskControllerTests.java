package ru.egartech.employeemanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.egartech.employeemanagementsystem.dto.TaskDto;
import ru.egartech.employeemanagementsystem.model.Employee;
import ru.egartech.employeemanagementsystem.model.Task;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void shouldReturnAllTasks() {
    ResponseEntity<TaskDto[]> response = restTemplate.getForEntity("/api/tasks", TaskDto[].class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotEmpty();
  }

  @Test
  void shouldReturnTaskById() {
    Long taskId = 1L;
    ResponseEntity<TaskDto> response = restTemplate.getForEntity("/api/tasks/" + taskId,
        TaskDto.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
  }

  @Test
  void shouldCreateTask() {
    Task newTask = new Task();
    newTask.setTitle("New Task");
    newTask.setDescription("Task description");
    newTask.setStatus("IN_PROGRESS");

    Employee employee = new Employee();
    employee.setId(3L);
    newTask.setEmployee(employee);

    ResponseEntity<TaskDto> response = restTemplate.postForEntity("/api/tasks", newTask,
        TaskDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getTitle()).isEqualTo("New Task");
  }

  @Test
  void shouldUpdateTask() {
    Long taskId = 1L;
    Task updatedTask = new Task();
    updatedTask.setTitle("Updated Task");
    updatedTask.setDescription("Updated task description");
    updatedTask.setStatus("COMPLETED");

    Employee employee = new Employee();
    employee.setId(3L);
    updatedTask.setEmployee(employee);

    restTemplate.put("/api/tasks/" + taskId, updatedTask);

    ResponseEntity<TaskDto> response = restTemplate.getForEntity("/api/tasks/" + taskId,
        TaskDto.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getTitle()).isEqualTo("Updated Task");
    assertThat(response.getBody().getStatus()).isEqualTo("COMPLETED");
  }

    /*@Test
    void shouldDeleteTask() {
        Task newTask = new Task();
        newTask.setTitle("Task for Deletion");
        newTask.setDescription("Task description");
        newTask.setStatus("IN_PROGRESS");

        Employee employee = new Employee();
        employee.setId(3L);
        newTask.setEmployee(employee);

        // Создаем задачу, чтобы потом удалить
        ResponseEntity<TaskDto> createResponse = restTemplate.postForEntity("/api/tasks", newTask, TaskDto.class);
        Long taskId = createResponse.getBody().getId();

        // Удаляем созданную задачу
        restTemplate.delete("/api/tasks/" + taskId);

        // Проверяем, что задача удалена
        ResponseEntity<String> getResponse = restTemplate.getForEntity("/api/tasks/" + taskId, String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }*/
}
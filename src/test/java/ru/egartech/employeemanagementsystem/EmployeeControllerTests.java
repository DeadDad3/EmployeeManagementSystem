package ru.egartech.employeemanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.egartech.employeemanagementsystem.dto.EmployeeDto;
import ru.egartech.employeemanagementsystem.model.Employee;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void shouldReturnAllEmployees() {
    ResponseEntity<EmployeeDto[]> response = restTemplate.getForEntity("/api/employees",
        EmployeeDto[].class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotEmpty();
  }

  @Test
  void shouldReturnEmployeeById() {
    Long employeeId = 3L;
    ResponseEntity<EmployeeDto> response = restTemplate.getForEntity("/api/employees/" + employeeId,
        EmployeeDto.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
  }

  @Test
  void shouldCreateEmployee() {
    Employee newEmployee = new Employee();
    newEmployee.setFirstName("Mark");
    newEmployee.setLastName("Taylor");
    newEmployee.setPosition("Junior Developer");
    newEmployee.setEmail("mark.taylor.unique@example.com");

    ResponseEntity<EmployeeDto> response = restTemplate.postForEntity("/api/employees", newEmployee,
        EmployeeDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getEmail()).isEqualTo("mark.taylor.unique@example.com");
  }

  @Test
  void shouldGetAllEmployees() {
    ResponseEntity<EmployeeDto[]> response = restTemplate.getForEntity("/api/employees",
        EmployeeDto[].class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotEmpty();
  }

  @Test
  void shouldGetEmployeeById() {
    long employeeId = 3;
    ResponseEntity<EmployeeDto> response = restTemplate.getForEntity("/api/employees/" + employeeId,
        EmployeeDto.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getId()).isEqualTo(employeeId);
  }

  @Test
  void shouldUpdateEmployee() {
    long employeeId = 3;
    Employee updatedEmployee = new Employee();
    updatedEmployee.setFirstName("Updated Name");
    updatedEmployee.setLastName("Updated LastName");
    updatedEmployee.setPosition("Updated Position");

    restTemplate.put("/api/employees/" + employeeId, updatedEmployee);
    ResponseEntity<EmployeeDto> response = restTemplate.getForEntity("/api/employees/" + employeeId,
        EmployeeDto.class);

    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody().getFirstName()).isEqualTo("Updated Name");
  }

    /*@Test
    void shouldDeleteEmployee() {
        // Создаем нового сотрудника для удаления с уникальным email
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("TestDelete");
        employeeDto.setLastName("Employee");
        employeeDto.setPosition("Tester");
        employeeDto.setEmail("testdelete" + System.currentTimeMillis() + "@example.com"); // уникальный email

        // Отправляем POST запрос на создание сотрудника
        ResponseEntity<EmployeeDto> createResponse = restTemplate.postForEntity("/api/employees", employeeDto, EmployeeDto.class);
        Long employeeId = createResponse.getBody().getId();

        // Логируем результат создания сотрудника
        System.out.println("Создан сотрудник с ID: " + employeeId);

        // Проверяем, что сотрудник был успешно создан
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(employeeId).isNotNull();

        // Логируем перед удалением
        System.out.println("Удаляем сотрудника с ID: " + employeeId);

        // Отправляем DELETE запрос на удаление сотрудника
        restTemplate.delete("/api/employees/" + employeeId);

        // Логируем после удаления
        System.out.println("Сотрудник с ID " + employeeId + " был удален");

        // Отправляем GET запрос для проверки, что сотрудник был удален
        ResponseEntity<String> getResponse = restTemplate.getForEntity("/api/employees/" + employeeId, String.class);

        // Логируем результат GET запроса
        System.out.println("Результат GET запроса для удаленного сотрудника: " + getResponse.getStatusCode());

        // Ожидаем 404 NOT_FOUND, так как сотрудник должен быть удален
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }*/
}

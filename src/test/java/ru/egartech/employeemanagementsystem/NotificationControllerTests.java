package ru.egartech.employeemanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.egartech.employeemanagementsystem.dto.NotificationDto;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationControllerTests {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  void shouldReturnAllNotifications() {
    ResponseEntity<NotificationDto[]> response = restTemplate.getForEntity("/api/notifications",
        NotificationDto[].class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotEmpty();
  }

  @Test
  void shouldReturnNotificationById() {
    Long notificationId = 1L;
    ResponseEntity<NotificationDto> response = restTemplate.getForEntity(
        "/api/notifications/" + notificationId, NotificationDto.class);
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull();
  }
}
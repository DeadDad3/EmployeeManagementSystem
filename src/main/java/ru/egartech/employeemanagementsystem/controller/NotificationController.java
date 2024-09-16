package ru.egartech.employeemanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.egartech.employeemanagementsystem.dto.NotificationDto;
import ru.egartech.employeemanagementsystem.model.Notification;
import ru.egartech.employeemanagementsystem.service.impl.NotificationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification Controller", description = "API для работы с уведомлениями")
public class NotificationController {

  private final NotificationService notificationService;

  @Autowired
  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @Operation(summary = "Получить все уведомления")
  @GetMapping
  public List<NotificationDto> getAllNotifications() {
    return notificationService.getAllNotifications().stream()
        .map(notificationService::convertToDto)
        .collect(Collectors.toList());
  }

  @Operation(summary = "Получить уведомление по ID")
  @GetMapping("/{id}")
  public NotificationDto getNotificationById(@PathVariable Long id) {
    Notification notification = notificationService.getNotificationById(id)
        .orElseThrow(() -> new RuntimeException("Уведомление не найдено"));
    return notificationService.convertToDto(notification);
  }

  @Operation(summary = "Создать новое уведомление")
  @PostMapping
  public NotificationDto createNotification(@RequestBody NotificationDto notificationDto) {
    Notification createdNotification = notificationService.createNotification(notificationDto);
    return notificationService.convertToDto(createdNotification);
  }

  @Operation(summary = "Обновить уведомление по ID")
  @PutMapping("/{id}")
  public NotificationDto updateNotification(@PathVariable Long id,
      @RequestBody Notification notification) {
    Notification updatedNotification = notificationService.updateNotification(id, notification);
    return notificationService.convertToDto(updatedNotification);
  }

  @Operation(summary = "Удалить уведомление по ID")
  @DeleteMapping("/{id}")
  public void deleteNotification(@PathVariable Long id) {
    notificationService.deleteNotification(id);
  }
}
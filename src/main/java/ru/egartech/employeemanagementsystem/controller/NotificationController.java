package ru.egartech.employeemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.egartech.employeemanagementsystem.dto.NotificationDto;
import ru.egartech.employeemanagementsystem.model.Notification;
import ru.egartech.employeemanagementsystem.service.impl.NotificationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public List<NotificationDto> getAllNotifications() {
        return notificationService.getAllNotifications().stream()
                .map(notificationService::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public NotificationDto getNotificationById(@PathVariable Long id) {
        Notification notification = notificationService.getNotificationById(id)
                .orElseThrow(() -> new RuntimeException("Уведомление не найдено"));
        return notificationService.convertToDto(notification);
    }

    @PostMapping
    public NotificationDto createNotification(@RequestBody NotificationDto notificationDto) {
        Notification createdNotification = notificationService.createNotification(notificationDto);
        return notificationService.convertToDto(createdNotification);
    }

    @PutMapping("/{id}")
    public NotificationDto updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        Notification updatedNotification = notificationService.updateNotification(id, notification);
        return notificationService.convertToDto(updatedNotification);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }
}

package ru.egartech.employeemanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.egartech.employeemanagementsystem.model.Notification;
import ru.egartech.employeemanagementsystem.repository.NotificationRepository;
import ru.egartech.employeemanagementsystem.service.TelegramService;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final TelegramService telegramService;

    @Autowired
    public NotificationController(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @GetMapping("/send")
    public String sendNotification() {
        telegramService.sendMessage("Ваше уведомление отправлено!");
        return "Уведомление отправлено!";
    }

    @PostMapping
    public Notification createNotification(@RequestBody Notification notification) {
        return notificationRepository.save(notification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notificationDetails) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id " + id));
        notification.setType(notificationDetails.getType());
        notification.setContent(notificationDetails.getContent());
        notification.setStatus(notificationDetails.getStatus());
        notification.setEmployee(notificationDetails.getEmployee());
        final Notification updatedNotification = notificationRepository.save(notification);
        return ResponseEntity.ok(updatedNotification);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found with id " + id));
        notificationRepository.delete(notification);
        return ResponseEntity.noContent().build();
    }
}

package ru.egartech.employeemanagementsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.employeemanagementsystem.dto.NotificationDto;
import ru.egartech.employeemanagementsystem.model.Notification;
import ru.egartech.employeemanagementsystem.repository.NotificationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id);
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification updateNotification(Long id, Notification updatedNotification) {
        return notificationRepository.findById(id)
                .map(notification -> {
                    notification.setType(updatedNotification.getType());
                    notification.setContent(updatedNotification.getContent());
                    notification.setStatus(updatedNotification.getStatus());
                    return notificationRepository.save(notification);
                })
                .orElseThrow(() -> new RuntimeException("Уведомление не найдено"));
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }

    public NotificationDto convertToDto(Notification notification) {
        NotificationDto dto = new NotificationDto();
        dto.setId(notification.getId());
        dto.setType(notification.getType());
        dto.setContent(notification.getContent());
        dto.setStatus(notification.getStatus());
        dto.setEmployeeId(notification.getEmployee().getId());
        return dto;
    }
}
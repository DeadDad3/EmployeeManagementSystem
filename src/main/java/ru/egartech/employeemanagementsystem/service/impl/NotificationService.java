package ru.egartech.employeemanagementsystem.service.impl;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.egartech.employeemanagementsystem.dto.NotificationDto;
import ru.egartech.employeemanagementsystem.model.Employee;
import ru.egartech.employeemanagementsystem.model.Notification;
import ru.egartech.employeemanagementsystem.repository.EmployeeRepository;
import ru.egartech.employeemanagementsystem.repository.NotificationRepository;
import ru.egartech.employeemanagementsystem.service.TelegramService;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

  private final TelegramService telegramService;
  private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
  private final NotificationRepository notificationRepository;
  private final EmployeeRepository employeeRepository;

  public NotificationService(NotificationRepository notificationRepository,
      EmployeeRepository employeeRepository, TelegramService telegramService) {
    this.notificationRepository = notificationRepository;
    this.employeeRepository = employeeRepository;
    this.telegramService = telegramService;
  }

  public List<Notification> getAllNotifications() {
    return notificationRepository.findAll();
  }

  public Optional<Notification> getNotificationById(Long id) {
    return notificationRepository.findById(id);
  }

  public Notification createNotification(NotificationDto notificationDto) {
    Employee employee = employeeRepository.findById(notificationDto.getEmployeeId())
        .orElseThrow(() -> new RuntimeException("Сотрудник не найден"));

    Notification notification = new Notification();
    notification.setEmployee(employee);
    notification.setContent(notificationDto.getContent());
    notification.setType(notificationDto.getType());
    notification.setStatus("ожидает отправки");

    Notification savedNotification = notificationRepository.save(notification);

    try {
      String chatId = savedNotification.getEmployee().getChatId();
      telegramService.sendMessage(Long.parseLong(chatId), "У вас новая задача", null);
      savedNotification.setStatus("отправлено");
    } catch (Exception e) {
      savedNotification.setStatus("не удалось отправить");
      log.error("Ошибка отправки уведомления", e);
    }

    return notificationRepository.save(savedNotification);
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
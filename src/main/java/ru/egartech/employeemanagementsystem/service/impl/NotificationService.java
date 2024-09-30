package ru.egartech.employeemanagementsystem.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.egartech.employeemanagementsystem.dto.NotificationDto;
import ru.egartech.employeemanagementsystem.mapper.NotificationMapper;
import ru.egartech.employeemanagementsystem.model.Notification;
import ru.egartech.employeemanagementsystem.repository.NotificationRepository;
import ru.egartech.employeemanagementsystem.service.TelegramService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {
  private final NotificationRepository notificationRepository;
  private final NotificationMapper notificationMapper;
  private final TelegramService telegramService;
  private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

  @Autowired
  public NotificationService(NotificationRepository notificationRepository,
      NotificationMapper notificationMapper,
      TelegramService telegramService) {
    this.notificationRepository = notificationRepository;
    this.notificationMapper = notificationMapper;
    this.telegramService = telegramService;
  }

  public NotificationDto createNotification(NotificationDto notificationDto) {
    Notification notification = notificationMapper.toEntity(notificationDto);
    Notification savedNotification = notificationRepository.save(notification);

    telegramService.sendNotification(savedNotification);
    log.info("Уведомление создано и отослано в телеграм: {}", notification.getMessage());

    return notificationMapper.toDto(savedNotification);
  }

  public NotificationDto getNotification(Long id) {
    return notificationRepository.findById(id)
        .map(notificationMapper::toDto)
        .orElseThrow(() -> new RuntimeException("Уведомление не найдено"));
  }

  public List<NotificationDto> getAllNotifications() {
    return notificationRepository.findAll()
        .stream()
        .map(notificationMapper::toDto)
        .collect(Collectors.toList());
  }

  public NotificationDto updateNotification(Long id, NotificationDto notificationDto) {
    Notification existingNotification = notificationRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Уведомление не найдено"));
    Notification updatedNotification = notificationMapper.toEntity(notificationDto);
    updatedNotification.setId(existingNotification.getId());
    Notification savedNotification = notificationRepository.save(updatedNotification);

    return notificationMapper.toDto(savedNotification);
  }

  public void deleteNotification(Long id) {
    notificationRepository.deleteById(id);
  }
}
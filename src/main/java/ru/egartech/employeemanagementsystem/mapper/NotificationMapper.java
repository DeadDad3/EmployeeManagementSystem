package ru.egartech.employeemanagementsystem.mapper;

import org.mapstruct.Mapper;
import ru.egartech.employeemanagementsystem.dto.NotificationDto;
import ru.egartech.employeemanagementsystem.model.Notification;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
  NotificationDto toDto(Notification notification);
  Notification toEntity(NotificationDto notificationDto);
}
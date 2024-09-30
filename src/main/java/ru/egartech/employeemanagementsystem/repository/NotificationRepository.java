package ru.egartech.employeemanagementsystem.repository;

import ru.egartech.employeemanagementsystem.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
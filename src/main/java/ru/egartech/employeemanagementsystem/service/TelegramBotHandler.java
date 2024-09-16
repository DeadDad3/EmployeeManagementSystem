package ru.egartech.employeemanagementsystem.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.egartech.employeemanagementsystem.model.Employee;
import ru.egartech.employeemanagementsystem.repository.EmployeeRepository;

@Service
public class TelegramBotHandler {

  private final EmployeeRepository employeeRepository;

  private static final Logger log = LoggerFactory.getLogger(TelegramBotHandler.class);

  public TelegramBotHandler(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public void handleResponse(Update update) {
    Long chatId = update.getMessage().getChatId();
    String response = update.getMessage().getText();

    Employee employee = employeeRepository.findByChatId(chatId.toString());
    if (employee == null) {
      log.error("Сотрудник с chatId {} не найден в базе данных", chatId);
      return;
    }

    switch (response) {
      case "На высоте, готов к любым задачам!":
        log.info("Сотрудник в хорошем настроении: {}", employee.getUsername());
        break;
      case "Так себе, но работу выполнять надо.":
      case "Ниже плинтуса, но это не связано с работой.":
        break;
      case "Ужасно, ибо в работе появилось полно проблем.":
        notifyManager(employee);
        break;
      default:
        log.warn("Неизвестный ответ от сотрудника: {}", response);
        break;
    }
  }

  private void notifyManager(Employee employee) {
    // Логика уведомления руководителя
  }
}

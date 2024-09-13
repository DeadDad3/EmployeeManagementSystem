package ru.egartech.employeemanagementsystem.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.egartech.employeemanagementsystem.model.Employee;
import ru.egartech.employeemanagementsystem.repository.EmployeeRepository;

@Service
public class TelegramService extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(TelegramService.class);

    private final EmployeeRepository employeeRepository;

    public TelegramService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String getBotToken() {
        return "7001192841:AAG0UWN61pcnKX8IWXURtilZAhrosNwyc08";
    }

    @Override
    public String getBotUsername() {
        return "Гуру Мотивации";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String username = update.getMessage().getFrom().getUserName();

            Employee employee = employeeRepository.findByChatId(chatId.toString());
            if (employee == null) {
                employee = new Employee();
                employee.setChatId(chatId.toString());
                employee.setUsername(username);
                employeeRepository.save(employee);
            }

            sendMessage(chatId, "Добро пожаловать, " + username + "!", null);
        }
    }

    public void sendMessage(Long chatId, String messageText, ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(messageText);
        message.setReplyMarkup(keyboardMarkup);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Ошибка отправки сообщения", e);
        }
    }
}

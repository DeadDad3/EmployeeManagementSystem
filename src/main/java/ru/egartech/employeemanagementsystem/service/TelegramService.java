package ru.egartech.employeemanagementsystem.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelegramService extends TelegramLongPollingBot {

  private static final Logger log = LoggerFactory.getLogger(TelegramService.class);
  private final TelegramBotHandler telegramBotHandler;

  public TelegramService(TelegramBotHandler telegramBotHandler) {
    this.telegramBotHandler = telegramBotHandler;
  }

  @Override
  public String getBotToken() {
    return "7001192841:AAGwuqsGLx_6NHpFK3F52jrGHsv50vWntik";
  }

  @Override
  public String getBotUsername() {
    return "motivation_guru_bot";
  }

  public void sendMessage(Long chatId, String messageText, ReplyKeyboardMarkup keyboardMarkup) {
    SendMessage message = new SendMessage();
    message.setChatId(chatId.toString());
    message.setText(messageText);
    message.setReplyMarkup(keyboardMarkup);

    try {
      execute(message);
      log.info("Сообщение успешно отправлено пользователю с chatId: {}", chatId);
    } catch (TelegramApiException e) {
      log.error("Ошибка отправки сообщения", e);
    }

  }

  @Override
  public void onUpdateReceived(Update update) {
    if (update.hasMessage() && update.getMessage().hasText()) {
      Long chatId = update.getMessage().getChatId();
      String messageText = update.getMessage().getText();

      if (messageText.equals("/start")) {
        String welcomeMessage = "Привет! Я ваш бот для мониторинга настроений. Как ваше настроение сегодня?";

        sendQuestionWithButtons(chatId, welcomeMessage);
      } else {
        telegramBotHandler.handleResponse(update);
      }
    }
  }

  private void sendQuestionWithButtons(Long chatId, String messageText) {
    SendMessage message = new SendMessage();
    message.setChatId(chatId.toString());
    message.setText(messageText);

    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
    List<KeyboardRow> keyboard = new ArrayList<>();

    KeyboardRow row1 = new KeyboardRow();
    row1.add("На высоте, готов к любым задачам!");
    row1.add("Так себе, но работу выполнять надо.");

    KeyboardRow row2 = new KeyboardRow();
    row2.add("Ниже плинтуса, но это не связано с работой.");
    row2.add("Ужасно, ибо в работе появилось полно проблем.");

    keyboard.add(row1);
    keyboard.add(row2);
    keyboardMarkup.setKeyboard(keyboard);

    message.setReplyMarkup(keyboardMarkup);

    try {
      execute(message);
    } catch (TelegramApiException e) {
      log.error("Ошибка отправки сообщения", e);
    }
  }
}

package ru.tinkoff.edu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.tinkoff.edu.config.BotConfig;

import java.util.HashMap;
import java.util.Map;

@Component("BOT")
public class Bot {


    BotCommand[] commands = {
            new BotCommand("start", "зарегестрировать пользователя"),
            new BotCommand("help", "вывести окно с командами"),
            new BotCommand("track", "начать отслеживание ссылки"),
            new BotCommand("untrack", "прекратить отслеживание ссылки"),
            new BotCommand("list", "показать список отслеживаемых ссылок")
    };


    @Value("${app.token}")
    private String test;

    TelegramBot bot = new TelegramBot(test);


    public Bot() {
        System.out.println("5610905224:AAGEWwaRMVb3O-d_dj7rUSxOzwhj7LX4Wm8" + test);
//        bot = new TelegramBot("TOKEN");
//        bot.execute(new SetMyCommands(commands));
//        bot.setUpdatesListener(new BotUpdater(bot));
    }


    void start() {
        bot = new TelegramBot("TOKEN");
        bot.execute(new SetMyCommands(commands));
        bot.setUpdatesListener(new BotUpdater(bot));

    }

    String getTest() {
        return test;

    }
}

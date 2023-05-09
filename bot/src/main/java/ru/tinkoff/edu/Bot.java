package ru.tinkoff.edu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
        System.out.println("+++++++TOKEN " + test);
//        bot = new TelegramBot("TOKEN");
//        bot.execute(new SetMyCommands(commands));
//        bot.setUpdatesListener(new BotUpdater(bot));
    }


    void start() {
        bot = new TelegramBot(getTest());
        bot.execute(new SetMyCommands(commands));
        bot.setUpdatesListener(new BotUpdater(bot));

    }

    String getTest() {
        return test;

    }
}

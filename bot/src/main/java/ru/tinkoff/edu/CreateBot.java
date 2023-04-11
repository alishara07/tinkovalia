package ru.tinkoff.edu;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SetMyCommands;


public class CreateBot {
    BotCommand[] commands = {
            new BotCommand("start", "зарегестрировать пользователя"),
            new BotCommand("help", "вывести окно с командами"),
            new BotCommand("track", "начать отслеживание ссылки"),
            new BotCommand("untrack", "прекратить отслеживание ссылки"),
            new BotCommand("list", "показать список отслеживаемых ссылок")
    };
    CreateBot(String token){
        /*
        * git branch -m hw3
git add .
git commit -m "что-то"
git push -u origin hw3*/
        TelegramBot bot = new TelegramBot(token);
        bot.execute(new SetMyCommands(commands));
        bot.setUpdatesListener(new BotUpdater(bot));
    }
}

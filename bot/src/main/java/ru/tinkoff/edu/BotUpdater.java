package ru.tinkoff.edu;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;

import java.io.IOException;
import java.util.List;

public class BotUpdater implements UpdatesListener {
    String comand;
    TelegramBot bot;
    int updateid_fromComand = 0;
    public BotUpdater (TelegramBot bot){
        this.bot = bot;
    }

    @Override
    public int process(List<Update> updates) {
            updates.forEach(update ->{

                String msg = update.message().text();
                System.out.println(update.message().chat().username() + " " + msg);


                if (msg == null) msg = "/help";

                switch (msg){
                    case "/start" -> {
                        start(update);
                        bot.execute(new SendMessage(update.message().chat().id(), "start"));
                    }
                    case "/help" -> {
                        bot.execute(new SendMessage(update.message().chat().id(), "никто не поможет"));
                    }
                    case "/track" -> {
                        bot.execute(new SendMessage(update.message().chat().id(), "type link"));
                        update.message().messageId();
                        updateid_fromComand = update.message().messageId();
                        comand = msg;
                    }
                    case "/untrack" -> {
                        bot.execute(new SendMessage(update.message().chat().id(), "type link"));
                        updateid_fromComand = update.message().messageId();
                        comand = msg;
                    }
                    case "/list" -> {
                        bot.execute(new SendMessage(update.message().chat().id(), "list"));
                    }
                    default ->{
                        if (update.message().messageId() == updateid_fromComand + 2){
                            switch (comand){
                                case "/track" -> {
                                    bot.execute(new SendMessage(update.message().chat().id(), "track"));

                                }
                                case "/untrack" -> bot.execute(new SendMessage(update.message().chat().id(), "untrack"));

                            }

                        }
                        else bot.execute(new SendMessage(update.message().chat().id(), "введена хрень"));
                    }

                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        }

        void start(Update update){

            bot.execute(new SendMessage(update.message().chat().id(), "зарегали команду старт"));

        }

    }




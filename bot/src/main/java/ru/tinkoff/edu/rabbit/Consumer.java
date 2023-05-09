package ru.tinkoff.edu.rabbit;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.CreateBot;
import ru.tinkoff.edu.dto.AddLinkRequest;
import ru.tinkoff.edu.dto.LinkUpdaterRequest;
import ru.tinkoff.edu.dto.ListLinksResponse;

import java.util.Arrays;

@Component
public class Consumer {

    @RabbitListener(queues = "update")
    public void listen(LinkUpdaterRequest request) {
        TelegramBot bot = CreateBot.getBot();
        System.err.println("from rabbit " + request);
        for (Integer chatid : request.tgChatIds()) {
            bot.execute(new SendMessage(chatid, "По вашей ссылке  " + request.url() + " произошло обновление "
                    + request.description()));
        }
    }
    @RabbitListener(queues = "listResponse")
    public void list(@Payload ListLinksResponse in, @Header("chatId") Long id) {
        System.err.println(in);
        StringBuilder response = new StringBuilder("Ваши отслеживаемы ссылки: \n");
        in.links().forEach(link-> response.append(link.url()).append("\n"));
        TelegramBot bot = CreateBot.getBot();
        bot.execute(new SendMessage(id, response.toString()));
    }


}

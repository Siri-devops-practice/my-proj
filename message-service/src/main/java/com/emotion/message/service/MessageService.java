package com.emotion.message.service;

import com.emotion.message.model.Message;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class MessageService {

    public Message getSorryMessage() {
        Message message = new Message();
        message.setMessageId(UUID.randomUUID().toString());
        message.setType("SORRY");
        message.setContent("I'M SORRY ❤️ ❤️ ❤️");
        message.setEmoji("😔");
        message.setTimestamp(System.currentTimeMillis());
        return message;
    }

    public Message getMissYouMessage() {
        Message message = new Message();
        message.setMessageId(UUID.randomUUID().toString());
        message.setType("MISS_YOU");
        message.setContent("I MISS YOU ❤️ ❤️ ❤️");
        message.setEmoji("😢");
        message.setTimestamp(System.currentTimeMillis());
        return message;
    }

    public Message getHeartMessage() {
        Message message = new Message();
        message.setMessageId(UUID.randomUUID().toString());
        message.setType("LOVE");
        message.setContent("I LOVE YOU ❤️ ❤️ ❤️");
        message.setEmoji("❤️");
        message.setTimestamp(System.currentTimeMillis());
        return message;
    }
}

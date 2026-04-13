package com.emotion.message.service;

import com.emotion.message.model.Message;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageServiceTest {

    private final MessageService messageService = new MessageService();

    @Test
    void shouldReturnSorryMessage() {
        Message message = messageService.getSorryMessage();

        assertThat(message).isNotNull();
        assertThat(message.getType()).isEqualTo("SORRY");
        assertThat(message.getContent()).contains("I'M SORRY");
        assertThat(message.getEmoji()).isEqualTo("😔");
        assertThat(message.getMessageId()).isNotBlank();
        assertThat(message.getTimestamp()).isPositive();
    }

    @Test
    void shouldReturnMissYouMessage() {
        Message message = messageService.getMissYouMessage();

        assertThat(message.getType()).isEqualTo("MISS_YOU");
        assertThat(message.getContent()).contains("I MISS YOU");
        assertThat(message.getEmoji()).isEqualTo("😢");
    }

    @Test
    void shouldReturnHeartMessage() {
        Message message = messageService.getHeartMessage();

        assertThat(message.getType()).isEqualTo("LOVE");
        assertThat(message.getContent()).contains("I LOVE YOU");
        assertThat(message.getEmoji()).isEqualTo("❤️");
    }
}

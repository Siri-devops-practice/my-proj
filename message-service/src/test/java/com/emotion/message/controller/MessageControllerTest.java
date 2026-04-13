package com.emotion.message.controller;

import com.emotion.message.model.Message;
import com.emotion.message.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    void getSorryMessageShouldReturnMessage() throws Exception {
        Message message = new Message("1", "SORRY", "I'M SORRY ❤️", "😔", System.currentTimeMillis());
        when(messageService.getSorryMessage()).thenReturn(message);

        mockMvc.perform(get("/api/messages/sorry"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("SORRY"))
                .andExpect(jsonPath("$.content").value("I'M SORRY ❤️"))
                .andExpect(jsonPath("$.emoji").value("😔"));
    }

    @Test
    void getMissYouMessageShouldReturnMessage() throws Exception {
        Message message = new Message("2", "MISS_YOU", "I MISS YOU ❤️", "😢", System.currentTimeMillis());
        when(messageService.getMissYouMessage()).thenReturn(message);

        mockMvc.perform(get("/api/messages/miss-you"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("MISS_YOU"))
                .andExpect(jsonPath("$.emoji").value("😢"));
    }
}

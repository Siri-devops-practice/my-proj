package com.emotion.user.controller;

import com.emotion.user.model.UserReaction;
import com.emotion.user.service.UserReactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReactionController.class)
public class ReactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserReactionService userReactionService;

    @Test
    void recordReactionReturnsSavedReaction() throws Exception {
        UserReaction reaction = new UserReaction("1", "LOVE", "CLICKED", System.currentTimeMillis(), "session-1");
        when(userReactionService.recordReaction(anyString(), anyString())).thenReturn(reaction);

        mockMvc.perform(post("/api/reactions/record?messageType=LOVE&reactionType=CLICKED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.messageType").value("LOVE"))
                .andExpect(jsonPath("$.reactionType").value("CLICKED"));
    }

    @Test
    void getTotalClicksReturnsCurrentCount() throws Exception {
        when(userReactionService.getTotalClicks()).thenReturn(5);

        mockMvc.perform(get("/api/reactions/total-clicks"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }
}

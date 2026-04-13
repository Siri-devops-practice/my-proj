package com.emotion.user.service;

import com.emotion.user.model.UserReaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserReactionServiceTest {

    private UserReactionService userReactionService;

    @BeforeEach
    void setUp() {
        userReactionService = new UserReactionService();
    }

    @Test
    void shouldRecordReactionAndIncreaseTotalCount() {
        UserReaction reaction = userReactionService.recordReaction("LOVE", "CLICKED");

        assertThat(reaction).isNotNull();
        assertThat(reaction.getMessageType()).isEqualTo("LOVE");
        assertThat(reaction.getReactionType()).isEqualTo("CLICKED");
        assertThat(userReactionService.getTotalClicks()).isEqualTo(1);
    }

    @Test
    void shouldReturnCorrectClicksForMessageType() {
        userReactionService.recordReaction("LOVE", "CLICKED");
        userReactionService.recordReaction("SORRY", "CLICKED");
        userReactionService.recordReaction("LOVE", "CLICKED");

        assertThat(userReactionService.getClicksForMessage("LOVE")).isEqualTo(2);
        assertThat(userReactionService.getClicksForMessage("SORRY")).isEqualTo(1);
    }

    @Test
    void shouldReturnReactionById() {
        UserReaction reaction = userReactionService.recordReaction("LOVE", "CLICKED");

        assertThat(userReactionService.getReaction(reaction.getReactionId())).isEqualTo(reaction);
    }
}

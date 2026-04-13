package com.emotion.user.service;

import com.emotion.user.model.UserReaction;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserReactionService {
    private Map<String, UserReaction> reactions = new HashMap<>();
    private int clickCounter = 0;

    public UserReaction recordReaction(String messageType, String reactionType) {
        String reactionId = UUID.randomUUID().toString();
        UserReaction reaction = new UserReaction();
        reaction.setReactionId(reactionId);
        reaction.setMessageType(messageType);
        reaction.setReactionType(reactionType);
        reaction.setTimestamp(System.currentTimeMillis());
        reaction.setUserSession(UUID.randomUUID().toString());
        
        reactions.put(reactionId, reaction);
        clickCounter++;
        
        return reaction;
    }

    public UserReaction getReaction(String reactionId) {
        return reactions.get(reactionId);
    }

    public int getTotalClicks() {
        return clickCounter;
    }

    public int getClicksForMessage(String messageType) {
        return (int) reactions.values().stream()
            .filter(r -> r.getMessageType().equals(messageType))
            .count();
    }
}

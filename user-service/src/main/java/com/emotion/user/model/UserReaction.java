package com.emotion.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReaction {
    private String reactionId;
    private String messageType;  // SORRY or MISS_YOU
    private String reactionType; // CLICKED, VIEWED, etc
    private long timestamp;
    private String userSession;
}

package com.emotion.message.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String messageId;
    private String type;  // SORRY or MISS_YOU
    private String content;
    private String emoji;
    private long timestamp;
}

package com.emotion.message.controller;

import com.emotion.message.model.Message;
import com.emotion.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/sorry")
    public ResponseEntity<Message> getSorryMessage() {
        return ResponseEntity.ok(messageService.getSorryMessage());
    }

    @GetMapping("/miss-you")
    public ResponseEntity<Message> getMissYouMessage() {
        return ResponseEntity.ok(messageService.getMissYouMessage());
    }

    @GetMapping("/love")
    public ResponseEntity<Message> getHeartMessage() {
        return ResponseEntity.ok(messageService.getHeartMessage());
    }
}

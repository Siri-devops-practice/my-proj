package com.emotion.user.controller;

import com.emotion.user.model.UserReaction;
import com.emotion.user.service.UserReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reactions")
@CrossOrigin(origins = "*")
public class ReactionController {

    @Autowired
    private UserReactionService userReactionService;

    @PostMapping("/record")
    public ResponseEntity<UserReaction> recordReaction(
            @RequestParam String messageType,
            @RequestParam String reactionType) {
        UserReaction reaction = userReactionService.recordReaction(messageType, reactionType);
        return ResponseEntity.ok(reaction);
    }

    @GetMapping("/total-clicks")
    public ResponseEntity<Integer> getTotalClicks() {
        return ResponseEntity.ok(userReactionService.getTotalClicks());
    }

    @GetMapping("/clicks/{messageType}")
    public ResponseEntity<Integer> getClicksForMessage(@PathVariable String messageType) {
        return ResponseEntity.ok(userReactionService.getClicksForMessage(messageType));
    }

    @GetMapping("/{reactionId}")
    public ResponseEntity<UserReaction> getReaction(@PathVariable String reactionId) {
        UserReaction reaction = userReactionService.getReaction(reactionId);
        if (reaction != null) {
            return ResponseEntity.ok(reaction);
        }
        return ResponseEntity.notFound().build();
    }
}

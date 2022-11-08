package com.hw.chatservice.domain.chat.api;

import com.hw.chatservice.domain.chat.application.ChatService;
import com.hw.chatservice.domain.chat.domain.Chat;
import com.hw.chatservice.domain.kafka.application.KafkaService;
import com.hw.chatservice.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatApi {

    private final ChatService chatService;
    private final KafkaService kafkaService;

    @GetMapping("/{clubName}")
    public List<Chat> chatHistory(@PathVariable String clubName) {
        List<Chat> chats = chatService.showHistory(clubName);
        return chats;
    }

    @MessageMapping("/message")
    public void sendMessage(@RequestBody Message message) {
        message.setCreatedAt(LocalDateTime.now());
        kafkaService.sendMessage(message);
    }

}

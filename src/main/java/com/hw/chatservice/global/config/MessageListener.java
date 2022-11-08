package com.hw.chatservice.global.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hw.chatservice.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MessageListener extends AbstractConsumerSeekAware {

//    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ObjectMapper objectMapper;

    @KafkaListener(id = "listener", topicPattern = ".*", groupId = "hw")
    public void listen(Message message) throws JsonProcessingException {
//        chatService.saveMessage(message);
        log.info(message.getContent());
        String jsonMessage = objectMapper.writeValueAsString(message);
        simpMessagingTemplate.convertAndSend("/topic/public", jsonMessage);
    }

}

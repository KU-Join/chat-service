package com.hw.chatservice.domain.kafka.application;

import com.hw.chatservice.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class KafkaService {

    private final KafkaListener kafkaListener;
    private KafkaTemplate<String, Message> kafkaTemplate;


    public KafkaService(final KafkaListenerContainerFactory kafkaListenerContainerFactory, KafkaTemplate kafkaTemplate) {
        this.kafkaListener = new KafkaListener(kafkaListenerContainerFactory);
        this.kafkaTemplate = kafkaTemplate;
    }

    public void start() {
        kafkaListener.start();
    }

    public void stop() {
        kafkaListener.stop();
    }

    public void registerListener(final Set<String> topic) {
        kafkaListener.register(() -> topic, () -> messageListener());
    }

    public void deRegisterListener(final Set<String> topic) {
        kafkaListener.deRegister(() -> topic);
    }

    private MessageListener<Object, Object> messageListener() {
        return (record) -> log.info("Order listened : {}", record);
    }

    public void sendMessage(Message message) {
        this.kafkaTemplate.send(message.getClubName(), message);
    }

}

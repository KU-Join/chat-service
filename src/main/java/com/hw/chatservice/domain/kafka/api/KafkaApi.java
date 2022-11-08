package com.hw.chatservice.domain.kafka.api;

import com.hw.chatservice.domain.kafka.application.KafkaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class KafkaApi {

    private final KafkaService kafkaService;

    @PostMapping("/start")
    public void restart() {
        kafkaService.start();
    }

    @PostMapping("/stop")
    public void stop() {
        kafkaService.stop();
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid Payload payload) {
        kafkaService.registerListener(payload.getTopics());
    }

    @PostMapping("/de-register")
    public void deRegister(@RequestBody @Valid Payload payload) {
        kafkaService.deRegisterListener(payload.getTopics());
    }

    private static class Payload {
        @NotEmpty
        @Size(min = 1)
        private Set<@Valid @NotEmpty String> topics;

        private Payload() {
        }

        public Payload(final Set<String> topics) {
            this.topics = topics;
        }

        public Set<String> getTopics() {
            return topics;
        }
    }

}

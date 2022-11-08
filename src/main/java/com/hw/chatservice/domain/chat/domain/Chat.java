package com.hw.chatservice.domain.chat.domain;

import com.hw.chatservice.model.Message;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat {

    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String clubName;

    private String content;

    private LocalDateTime createdAt;

    @Builder
    public Chat(String userName, String clubName, String content, LocalDateTime createdAt) {
        this.userName = userName;
        this.clubName = clubName;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static Chat of(Message message) {
        return Chat.builder()
                .userName(message.getUserName())
                .clubName(message.getClubName())
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .build();
    }

}

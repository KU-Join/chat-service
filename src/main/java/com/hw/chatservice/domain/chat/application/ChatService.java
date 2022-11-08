package com.hw.chatservice.domain.chat.application;

import com.hw.chatservice.domain.chat.dao.ChatRepository;
import com.hw.chatservice.domain.chat.domain.Chat;
import com.hw.chatservice.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {

    private final ChatRepository chatRepository;

    @Transactional
    public void saveMessage(Message message) {
        Chat chat = Chat.of(message);
        chatRepository.save(chat);
    }

    public List<Chat> showHistory(String clubName) {
        return chatRepository.findAllByClubName(clubName);
    }

}

package com.chat.service;

import com.chat.dto.MessageBody;
import com.chat.dto.MessageView;
import com.chat.model.Message;
import com.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
public class MessageService {

  @Autowired
  MessageRepository messageRepository;

  public Flux<MessageView> findWithTailableCursorByRoom(String room) {
    return messageRepository.findWithTailableCursorByRoom(room)
      .map(it -> new MessageView(it.text, "anonymous", LocalDateTime.now()));
  }


  public void newMessage(MessageBody messageBody) {
    this.messageRepository.save(new Message(null, messageBody.text, messageBody.room)).subscribe();
  }
}

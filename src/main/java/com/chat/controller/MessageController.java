package com.chat.controller;

import com.chat.dto.MessageBody;
import com.chat.dto.MessageView;
import com.chat.service.MessageService;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MessageController {

  @Autowired
  MessageService messageService;

  @GetMapping(value = "/messages", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<MessageView> allMessages(@RequestParam String room) {
    return messageService.findWithTailableCursorByRoom(room);
  }

  @PostMapping(value = "/messages")
  @ResponseStatus(HttpStatus.CREATED)
  public void newMessage(@RequestBody MessageBody messageBody) {
    this.messageService.newMessage(messageBody);
  }

  @RequestMapping("/greeting")
  public String createNewDatabase() {
    MongoClient mongoClient = new MongoClient();
    MongoDatabase database = mongoClient.getDatabase("chatApp");
    CreateCollectionOptions options = new CreateCollectionOptions();
    options.capped(true);
    options.sizeInBytes(5000000);
    options.maxDocuments(10000);
    database.createCollection("messages", options);

    return "success";
  }
}

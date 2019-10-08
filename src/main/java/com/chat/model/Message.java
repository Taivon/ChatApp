package com.chat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
public class Message {
  public String text;
  private String room;
  @Id
  private String id;

  public Message(String id, String text, String room) {
    this.id = id;
    this.text = text;
    this.room = room;
  }
}

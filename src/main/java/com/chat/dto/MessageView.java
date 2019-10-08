package com.chat.dto;

import java.time.LocalDateTime;

public class MessageView {
  private String text;
  private String user;
  private LocalDateTime timestamp;


  public MessageView(String text, String user, LocalDateTime timestamp) {
    this.text = text;
    this.user = user;
    this.timestamp = timestamp;
  }
}

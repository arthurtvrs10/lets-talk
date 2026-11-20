package com.backend.config;

import java.time.LocalDateTime;

public class ChatMessage {
    private String from;
    private String content;
    private LocalDateTime timestamp;

    public ChatMessage(String content, String from) {
        this.content = content;
        this.from = from;
        this.timestamp = LocalDateTime.now();
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}

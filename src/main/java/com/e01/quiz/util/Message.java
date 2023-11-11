package com.e01.quiz.util;

import com.e01.quiz.entity.TestHistory;

public class Message {

        private String from;
        private Object data;
        private EMessageType type;

    public Message(String username, TestHistory testHistory, EMessageType eMessageType) {
        this.from = username;
        this.data = testHistory;
        this.type = eMessageType;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString(){
        return "From: " + this.getFrom();
    }

    public EMessageType getType() {
        return type;
    }

    public void setType(EMessageType type) {
        this.type = type;
    }
}

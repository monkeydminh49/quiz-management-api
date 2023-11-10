package com.e01.quiz.util;

public class Message {

        private String from;
        private Object data;

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
        return "From: " + this.getFrom()
                + "\nData: " + this.getData().toString();
    }
}

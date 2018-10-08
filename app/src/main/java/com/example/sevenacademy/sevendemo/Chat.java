package com.example.sevenacademy.sevendemo;

import android.net.Uri;

public class Chat {

    String name;
    String message;
    long time;

    public Uri getImage() {
        return image;
    }

    private Uri image;

    public Chat() {
    }

    public Chat(String name, String message, long time) {
        this.name = name;
        this.message = message;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setImage(Uri path) {
        this.image = path;
    }
}

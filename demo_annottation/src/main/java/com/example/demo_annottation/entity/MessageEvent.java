package com.example.demo_annottation.entity;

import com.example.demo_annottation.Fields;
import com.example.demo_annottation.MyAnno;


@MyAnno(request = {"hello","world","annotation!"})
public class MessageEvent {
    @Fields("23412340")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

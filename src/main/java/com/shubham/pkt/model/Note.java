package com.shubham.pkt.model;

public class Note {

    private Long id;
    private String content;

    public Note() {}

    public Note(Long id, String content) {
        this.id = id;
        this.content = content;
    }
    public Long getId() { return id;}
    public String getContent() { return content; }
    public void setId(Long id) { this.id = id; }
    public void setContent(String content) { this.content = content; }
}

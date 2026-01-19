package com.shubham.pkt.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateNoteRequest {



    @NotBlank
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}

package com.shubham.pkt.controller;

import com.shubham.pkt.model.Note;
import com.shubham.pkt.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }
}
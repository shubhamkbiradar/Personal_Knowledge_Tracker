package com.shubham.pkt.controller;

import com.shubham.pkt.model.Note;
import com.shubham.pkt.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public Note createNote(@RequestBody String content) {
        return noteService.create(content);
    }

    @GetMapping
    public List<Note> all() {
        return noteService.findAll();
    }

}

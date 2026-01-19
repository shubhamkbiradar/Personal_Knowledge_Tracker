package com.shubham.pkt.controller;

import com.shubham.pkt.dto.CreateNoteRequest;
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

    @GetMapping
    public List<Note> getAll() {
        return noteService.findAll();
    }

    @GetMapping("/ping")
    public String ping() {
        return "ok";
    }
    @PostMapping
    public Note createNote(@RequestBody CreateNoteRequest request) {
        return noteService.create(request.getContent());
    }
}

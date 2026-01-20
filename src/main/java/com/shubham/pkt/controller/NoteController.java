package com.shubham.pkt.controller;

import com.shubham.pkt.dto.CreateNoteRequest;
import com.shubham.pkt.model.Note;
import com.shubham.pkt.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public Note getById(@PathVariable Long id) {
        return noteService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Note> createNote(
            @Valid @RequestBody CreateNoteRequest request) {

        Note created = noteService.create(request.getContent());

        URI location = URI.create("/notes/" + created.getId());

        return ResponseEntity
                .created(location)
                .body(created);
    }

    @PutMapping("/{id}")
    public Note updateNote(
            @PathVariable Long id,
            @Valid @RequestBody CreateNoteRequest request) {

        return noteService.update(id, request.getContent());
    }
}

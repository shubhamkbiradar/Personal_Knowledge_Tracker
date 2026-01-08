package com.shubham.pkt.service;

import com.shubham.pkt.model.Note;
import com.shubham.pkt.repository.NoteRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.List;

public class NoteService {

    private final NoteRepository repository;

    public NoteService(NoteRepository repository) {
        this.repository = repository;
    }

    public Note create(String content) {
        return repository.save(new Note(null, content));
    }

    public List<Note> findAll() {
        return repository.findAll();
    }

    @PostConstruct
    public void init() {
        System.out.println("INIT: " + this);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("DESTROY: " + this);
    }
}

package com.shubham.pkt.repository;

import com.shubham.pkt.model.Note;

import java.util.List;
import java.util.Optional;

public interface NoteRepository {
    Note save(Note note);
    List<Note> findAll();

    Optional<Note> findById(Long id);
    Note update(Long id, String content);
}

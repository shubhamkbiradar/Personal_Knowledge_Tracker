package com.shubham.pkt.repository;

import com.shubham.pkt.model.Note;

import java.util.List;

public interface NoteRepository {
    Note save(Note note);
    List<Note> findAll();
}

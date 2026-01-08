package com.shubham.pkt.repository;

import com.shubham.pkt.model.Note;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryNoteRepository implements NoteRepository {

    private final Map<Long, Note> store = new HashMap<>();
    private final AtomicLong idGen = new AtomicLong();

    @Override
    public Note save(Note note) {
        long id = idGen.incrementAndGet();
        Note saved = new Note(id, note.getContent());
        store.put(id, saved);
        return saved;
    }

    @Override
    public List<Note> findAll() {
        return new ArrayList<>(store.values());
    }
}

package com.shubham.pkt.repository;

import com.shubham.pkt.exception.NoteNotFoundException;
import com.shubham.pkt.model.Note;

import java.util.*;
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

    @Override
    public Optional<Note> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Note update(Long id, String content) {
        Note existing = store.get(id);
        if (existing == null) {
            throw new NoteNotFoundException(String.valueOf(id));
        }
        Note updated = new Note(id, content);
        store.put(id, updated);
        return updated;
    }
}

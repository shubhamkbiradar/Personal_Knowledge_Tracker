package com.shubham.pkt.repository;

import com.shubham.pkt.exception.NoteNotFoundException;
import com.shubham.pkt.model.Note;
import java.util.List;
import java.util.Optional;

public class JpaNoteRepositoryAdapter implements NoteRepository {

    private final JpaNoteRepository jpaRepository;

    public JpaNoteRepositoryAdapter(JpaNoteRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Note save(Note note) {
        return jpaRepository.save(note);
    }

    @Override
    public List<Note> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<Note> findById(Long id) {
        return jpaRepository.findById(id);
    }

    @Override
    public Note update(Long id, String content) {
        Note existing = jpaRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(String.valueOf(id)));
        existing.setContent(content);
        return jpaRepository.save(existing);
    }
}

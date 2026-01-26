package com.shubham.pkt.repository;

import com.shubham.pkt.model.Note;
import com.shubham.pkt.repository.JpaNoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JpaNoteRepositoryTest {

    @Autowired
    private JpaNoteRepository repo;

    @Test
    void saveAndFind_shouldWork() {
        Note saved = repo.save(new Note(null, "jpa test"));

        Optional<Note> found = repo.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("jpa test", found.get().getContent());
    }
}
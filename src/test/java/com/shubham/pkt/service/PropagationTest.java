package com.shubham.pkt.service;

import com.shubham.pkt.domain.note.Note;
import com.shubham.pkt.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PropagationTest {

    @Autowired
    NoteService noteService;

    @Autowired
    NoteRepository repo;


    @Test
    void requiresNew_shouldCommit_evenIfAuditFails() {

        Note note = noteService.create("test");

        assertEquals(1, repo.findAll().size());
        assertEquals("test", repo.findAll().get(0).getContent());
    }
}

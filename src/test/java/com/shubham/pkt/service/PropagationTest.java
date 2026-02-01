package com.shubham.pkt.service;

import com.shubham.pkt.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class PropagationTest {

    @Autowired
    NoteService noteService;

    @Autowired
    NoteRepository repo;


    @Test
    void requiresNew_shouldCommit_evenIfOuterFails()
    {
        assertThrows(RuntimeException.class,() -> noteService.createAndAudit("test"));
        assertEquals(1,repo.findAll().size());
    }
}

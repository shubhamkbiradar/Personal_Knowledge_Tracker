package com.shubham.pkt.service;

import com.shubham.pkt.context.RequestContext;
import com.shubham.pkt.domain.outbox.OutboxEvent;
import com.shubham.pkt.domain.outbox.OutboxRepository;
import com.shubham.pkt.exception.NoteNotFoundException;
import com.shubham.pkt.domain.note.Note;
import com.shubham.pkt.repository.NoteRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final AuditService auditService;
    private final ObjectProvider<RequestContext> requestContextProvider;

    public NoteService(
            NoteRepository noteRepository,
            AuditService auditService,
            ObjectProvider<RequestContext> requestContextProvider) {

        this.noteRepository = noteRepository;
        this.auditService = auditService;
        this.requestContextProvider = requestContextProvider;
    }


    @Transactional
    public Note create(String content) {

        RequestContext ctx = requestContextProvider.getObject();
        System.out.println("Using RequestContext: " + ctx);

        Note note = noteRepository.save(new Note(null, content));

        try {
            auditService.noteCreated(note);
        } catch (RuntimeException ex) {
            // intentional isolation
            // audit failed, but core business must commit
            System.out.println("Audit failed, continuing main flow");
        }

        return note;
    }



    @Transactional(readOnly = true)
    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Note findById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(String.valueOf(id)));
    }

    @Transactional
    public Note update(Long id, String content) {
        noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(String.valueOf(id)));

        return noteRepository.update(id, content);
    }
}


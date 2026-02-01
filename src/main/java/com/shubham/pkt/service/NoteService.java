package com.shubham.pkt.service;

import com.shubham.pkt.context.RequestContext;
import com.shubham.pkt.exception.NoteNotFoundException;
import com.shubham.pkt.model.Note;
import com.shubham.pkt.repository.NoteRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class NoteService {

    private final NoteRepository repository;

    private final AuditService auditService;

    //private final RequestContext requestContext;

    private final ObjectProvider<RequestContext> requestContextProvider;

    public NoteService(NoteRepository repository, AuditService auditService,
                       ObjectProvider<RequestContext> requestContextProvider) {
        this.repository = repository;
        this.auditService = auditService;
        this.requestContextProvider = requestContextProvider;
    }

    @Transactional
    public Note create(String content) {
        RequestContext ctx = requestContextProvider.getObject();
        System.out.println("Using RequestContext: " + ctx);
        return repository.save(new Note(null, content));
    }

    @Transactional
    public void createAndAudit(String content) {
       repository.save(new Note(null, content));
       auditService.audit(content);
       throw new RuntimeException(" Failed after Audit ");
    }


    @Transactional (readOnly = true )
    public List<Note> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true )
    public Note findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(String.valueOf(id)));
    }

    @Transactional
    public Note update(Long id, String content)
    {
        // existence check (truth ownership)
        repository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(String.valueOf(id)));

        // perform update
        return repository.update(id, content);
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

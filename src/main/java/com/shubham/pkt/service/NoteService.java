package com.shubham.pkt.service;

import com.shubham.pkt.context.RequestContext;
import com.shubham.pkt.exception.NoteNotFoundException;
import com.shubham.pkt.model.Note;
import com.shubham.pkt.repository.NoteRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.ObjectProvider;

import java.util.List;
import java.util.Optional;

public class NoteService {

    private final NoteRepository repository;

    //private final RequestContext requestContext;

    private final ObjectProvider<RequestContext> requestContextProvider;

    public NoteService(NoteRepository repository,
                       ObjectProvider<RequestContext> requestContextProvider) {
        this.repository = repository;
        this.requestContextProvider = requestContextProvider;
    }

    public Note create(String content) {
        RequestContext ctx = requestContextProvider.getObject();
        System.out.println("Using RequestContext: " + ctx);
        return repository.save(new Note(null, content));
    }


    public List<Note> findAll() {
        return repository.findAll();
    }

    public Note findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(String.valueOf(id)));
    }

    public Note update(Long id, String content)
    {
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

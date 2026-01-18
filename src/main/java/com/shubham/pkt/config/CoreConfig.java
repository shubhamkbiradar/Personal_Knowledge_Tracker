package com.shubham.pkt.config;

import com.shubham.pkt.context.RequestContext;
import com.shubham.pkt.repository.InMemoryNoteRepository;
import com.shubham.pkt.repository.NoteRepository;
import com.shubham.pkt.service.NoteService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CoreConfig {

    @Bean
    public NoteRepository noteRepository() {
        return new InMemoryNoteRepository();
    }

    @Bean
    public NoteService noteService(NoteRepository repo,
                                   ObjectProvider<RequestContext> provider) {
        return new NoteService(repo, provider);
    }

    @Bean
    @Scope("prototype")
    public RequestContext requestContext() {
            return new RequestContext();
    }
}

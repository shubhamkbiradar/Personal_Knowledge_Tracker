package com.shubham.pkt.service;

//import com.shubham.pkt.domain.note.Note;
import com.shubham.pkt.domain.note.Note;
import com.shubham.pkt.domain.outbox.OutboxEvent;
import com.shubham.pkt.domain.outbox.OutboxRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuditService {

    private final OutboxRepository outboxRepository;

    public AuditService(OutboxRepository outboxRepository) {
        this.outboxRepository = outboxRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void noteCreated(Note note) {

        OutboxEvent event = new OutboxEvent(
                "NOTE",
                note.getId(),
                "NOTE_CREATED",
                "{\"id\":" + note.getId() + "}"
        );

        outboxRepository.save(event);
        throw new RuntimeException("audit failed");

    }
}

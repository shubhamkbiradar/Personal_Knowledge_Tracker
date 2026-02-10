package com.shubham.pkt.domain.note;

import com.shubham.pkt.domain.outbox.OutboxEvent;

public class NoteEventFactory {

    public static OutboxEvent noteCreated(Note note) {
        return new OutboxEvent(
                "NOTE",
                note.getId(),
                "NOTE_CREATED",
                "{\"id\":" + note.getId() + "}"
        );
    }
}

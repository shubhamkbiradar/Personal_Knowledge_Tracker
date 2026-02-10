package com.shubham.pkt.domain.outbox;

import jakarta.persistence.*;

@Entity
@Table(name = "outbox_events")
public class OutboxEvent {

    @Id
    @GeneratedValue
    private Long id;

    private String aggregateType;   // "NOTE"
    private Long aggregateId;        // note.id
    private String eventType;        // "NOTE_CREATED"

    @Lob
    private String payload;          // JSON

    private boolean published = false;

    protected OutboxEvent() {}

    public OutboxEvent(String aggregateType, Long aggregateId,
                       String eventType, String payload) {
        this.aggregateType = aggregateType;
        this.aggregateId = aggregateId;
        this.eventType = eventType;
        this.payload = payload;
    }
    public Long getId() { return id; }
    public String getAggregateType() { return aggregateType; }
    public Long getAggregateId() { return aggregateId; }
    public String getEventType() { return eventType; }
    public String getPayload() { return payload; }
    public boolean isPublished() { return published; }

}

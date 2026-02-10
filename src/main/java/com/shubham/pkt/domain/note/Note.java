package com.shubham.pkt.domain.note;




import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    protected Note() { }

    public Note(Long id, String content) {
        this.id = id;
        this.content = content;
    }
    public Long getId() { return id;}
    public String getContent() { return content; }
    public void setId(Long id) { this.id = id; }
    public void setContent(String content) { this.content = content; }
}

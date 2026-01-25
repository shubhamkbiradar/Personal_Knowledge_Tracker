package com.shubham.pkt.repository;

import com.shubham.pkt.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNoteRepository extends JpaRepository<Note, Long> {
}

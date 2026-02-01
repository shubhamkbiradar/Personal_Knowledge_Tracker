package com.shubham.pkt.repository;


import com.shubham.pkt.exception.NoteNotFoundException;
import com.shubham.pkt.model.Note;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JpaNoteRepositoryAdapterTest {

    @Mock
    private JpaNoteRepository jpaRepository;

    @InjectMocks
    private JpaNoteRepositoryAdapter adapter;

    @Test
    void save_shouldDelegateToJpaRepository() {

        // ARRANGE
        Note input = new Note(null, "test");
        Note saved = new Note(1L, "test");

        when(jpaRepository.save(input)).thenReturn(saved);

        // ACT
        Note result = adapter.save(input);

        // ASSERT
        assertEquals(1L, result.getId());
        assertEquals("test", result.getContent());

        verify(jpaRepository, times(1)).save(input);
    }

    @Test
    void findById_shouldDelegate() {

        // ARRANGE
        Note note = new Note(1L, "test");
        when(jpaRepository.findById(1L))
                .thenReturn(Optional.of(note));

        // ACT
        Optional<Note> result = adapter.findById(1L);

        // ASSERT
        assertTrue(result.isPresent());
        assertEquals("test", result.get().getContent());
    }

    @Test
    void update_shouldThrowException_whenNoteMissing() {

        // ARRANGE
        when(jpaRepository.findById(99L))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        NoteNotFoundException ex = assertThrows(
                NoteNotFoundException.class,
                () -> adapter.update(99L, "new")
        );

        assertTrue(ex.getMessage().contains("99"));
    }

    @Test
    void update_shouldModifyAndSaveEntity() {

        // ARRANGE
        Note existing = new Note(1L, "old");
        when(jpaRepository.findById(1L))
                .thenReturn(Optional.of(existing));
        when(jpaRepository.save(existing))
                .thenReturn(existing);

        // ACT
        Note updated = adapter.update(1L, "new");

        // ASSERT
        assertEquals("new", updated.getContent());
        verify(jpaRepository).save(existing);
    }
}
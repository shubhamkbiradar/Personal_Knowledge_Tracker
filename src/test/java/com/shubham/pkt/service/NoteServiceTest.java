package com.shubham.pkt.service;

import com.shubham.pkt.context.RequestContext;
import com.shubham.pkt.exception.NoteNotFoundException;
import com.shubham.pkt.domain.note.Note;
import com.shubham.pkt.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.ObjectProvider;
import static org.mockito.Mockito.any;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

        @Mock
        private NoteRepository noteRepository;

        @Mock
        private ObjectProvider<RequestContext> requestContextProvider;

        @InjectMocks
        private NoteService noteService;

        @Test
        void findById_shouldReturnNote_whenNoteExists() {

                // ARRANGE
                Note note = new Note(1L, "test");
                when(noteRepository.findById(1L))
                        .thenReturn(Optional.of(note));

                // ACT
                Note result = noteService.findById(1L);

                // ASSERT
                assertNotNull(result);
                assertEquals(1L, result.getId());
                assertEquals("test", result.getContent());
        }

        @Test
        void findById_shouldThrowException_whenNoteMissing() {

                // ARRANGE
                when(noteRepository.findById(99L))
                        .thenReturn(Optional.empty());

                // ACT + ASSERT
                NoteNotFoundException ex = assertThrows(
                        NoteNotFoundException.class,
                        () -> noteService.findById(99L)
                );

                assertTrue(ex.getMessage().contains("99"));
        }

        @Test
        void updateNote_shouldUpdateNote_whenNoteExists() {

                // ARRANGE
                Note existingNote = new Note(1L, "old content");

                when(noteRepository.findById(1L))
                        .thenReturn(Optional.of(existingNote));

                when(noteRepository.update(1L, "new content"))
                        .thenReturn(new Note(1L, "new content"));

                // ACT
                Note updatedNote = noteService.update(1L, "new content");

                // ASSERT (state)
                assertNotNull(updatedNote);
                assertEquals(1L, updatedNote.getId());
                assertEquals("new content", updatedNote.getContent());

                // ASSERT (behavior)
                verify(noteRepository, times(1)).findById(1L);
                verify(noteRepository, times(1)).update(1L, "new content");
        }

        @Test
        void createNote_shouldUseRequestContext() {

                // ARRANGE
                RequestContext mockCtx = mock(RequestContext.class);
                when(requestContextProvider.getObject())
                        .thenReturn(mockCtx);

                when(noteRepository.save(any(Note.class)))
                        .thenAnswer(invocation -> {
                                Note arg = invocation.getArgument(0);
                                return new Note(1L, arg.getContent());
                        });

                // ACT
                Note createdNote = noteService.create("sample content");

                // ASSERT
                assertNotNull(createdNote);
                assertEquals(1L, createdNote.getId());
                assertEquals("sample content", createdNote.getContent());

                verify(requestContextProvider, times(1)).getObject();
                verify(noteRepository, times(1)).save(any(Note.class));
        }

}

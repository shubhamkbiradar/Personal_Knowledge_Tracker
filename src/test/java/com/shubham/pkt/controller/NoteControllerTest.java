package com.shubham.pkt.controller;

import com.shubham.pkt.exception.NoteNotFoundException;
import com.shubham.pkt.domain.note.Note;
import com.shubham.pkt.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)

public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Test
    void getNoteById_shouldReturn200_whenNoteExists() throws Exception {

        // ARRANGE
        when(noteService.findById(1L))
                .thenReturn(new Note(1L, "test"));

        // ACT + ASSERT
        mockMvc.perform(get("/notes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.content").value("test"));
    }
    @Test
    void getNoteById_shouldReturn404_whenNoteNotFound() throws Exception {

        when(noteService.findById(99L))
                .thenThrow(new NoteNotFoundException("99"));

        mockMvc.perform(get("/notes/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.details")
                        .value("Note not found with id: 99"));
    }
    @Test
    void createNote_shouldReturn400_whenContentBlank() throws Exception {

        String body = """
        {
          "content": ""
        }
        """;

        mockMvc.perform(post("/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details[0]")
                        .value("content: must not be blank"));
    }

}

package com.example.notes.repository;

import com.example.notes.entity.Note;
import com.example.notes.exception.NoteNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class NoteRepository {
    private List<Note> notes = new ArrayList<>();

    public List<Note> getAllNotes() {
        for (Note note : notes) {
            log.info(note.toString());
        }
        return notes;
    }

    public Note addNote(Note note) {
        Note newNote = copyOf(note);

        newNote.setId(GenerateId());
        notes.add(newNote);
        return newNote;
    }

    public void deleteNoteById(long id) throws NoteNotFoundException {
        Optional<Note> optionalNote = notes.stream()
                .filter(n -> n.getId() == id)
                .findFirst();
        if (optionalNote.isPresent()) {
            this.notes.remove(optionalNote.get());
        } else {
            throw new NoteNotFoundException(id);
        }
    }

    public void updateNote(Note note) throws NoteNotFoundException {
        Note newNote = copyOf(note);

        Optional<Note> optionalNote = notes.stream()
                .filter(n -> n.getId() == newNote.getId())
                .findFirst();
        if (optionalNote.isPresent()) {
            this.notes.remove(optionalNote.get());
            this.notes.add(newNote);
        } else {
            throw new NoteNotFoundException();
        }
    }

    public Note getNoteById(long id) throws NoteNotFoundException {
        Optional<Note> optionalNote = notes.stream()
                .filter(n -> n.getId() == id)
                .findFirst();
        if (optionalNote.isPresent()) {
            return optionalNote.get();
        } else {
            throw new NoteNotFoundException(id);
        }
    }

    private Long GenerateId() {
        Optional<Long> maxId = notes.stream().map(Note::getId).max(Long::compareTo);
        return maxId.map(aLong -> aLong + 1).orElse(1L);
    }

    private Note copyOf(Note note) {
        Note newNote = new Note();
        newNote.setId(note.getId());
        newNote.setTitle(note.getTitle());
        newNote.setContent(note.getContent());

        return newNote;
    }
}

package com.example.notes.service;

import com.example.notes.entity.Note;
import com.example.notes.exception.NoteNotFoundException;
import com.example.notes.repository.NoteRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> listAll() {
        return noteRepository.getAllNotes();
    }

    public Note add(Note note) {
        return noteRepository.addNote(note);
    }

    public void deleteById(long id) throws NoteNotFoundException {
        noteRepository.deleteNoteById(id);
    }

    public void update(Note note) throws NoteNotFoundException {
        noteRepository.updateNote(note);
    }

    public Note getById(long id) throws NoteNotFoundException {
        return noteRepository.getNoteById(id);
    }

    @PostConstruct
    public void init() {
        log.info("Hello");
        //add a new note
        Note note1 = new Note();
        note1.setTitle("title1");
        note1.setContent("content1");
        add(note1);

        Note note2 = new Note();
        note2.setTitle("title2");
        note2.setContent("content2");
        note2 = add(note2);

        Note note3 = new Note();
        note3.setTitle("title3");
        note3.setContent("content3");
        add(note3);
//
//        //list of all notes
//        log.info("list of all notes");
//        listAll();
//
//        //delete note by id
//        try {
//            deleteById(note2.getId());
//        } catch (NoteNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        Note note5 = new Note();
//        note5.setTitle("title5");
//        note5.setContent("content5");
//        add(note5);
//
//        log.info("list of all notes");
//        listAll();
//
//        long id = 3L;
//        try {
//            Note noteById = getById(id);
//            log.info("get note by id = " + noteById.toString());
//        } catch (NoteNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}

package com.example.notes.controller;

import com.example.notes.entity.Note;
import com.example.notes.exception.NoteNotFoundException;
import com.example.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/note")
public class NoteController {
    private final NoteService noteService;

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ModelAndView getNotes() {
        ModelAndView result = new ModelAndView("list");
        result.addObject("notes", noteService.listAll());
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public ModelAndView getCreateNote() {
        ModelAndView result = new ModelAndView("create-note");
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ModelAndView postCreateNote(
            @RequestParam(value = "noteTitle") String noteTitle,
            @RequestParam(value = "noteContent") String noteContent) {
        Note newNote = new Note();
        newNote.setTitle(noteTitle);
        newNote.setContent(noteContent);
        noteService.add(newNote);
        return getNotes();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public ModelAndView deleteNote(@RequestParam(value = "id") String id) throws NoteNotFoundException {
        noteService.deleteById(Long.parseLong(id));
        return getNotes();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public ModelAndView getEditNote(@RequestParam(value = "id") String id) throws NoteNotFoundException {
        ModelAndView result = new ModelAndView("edit-note");
        result.addObject("note", noteService.getById(Long.parseLong(id)));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit")
    public ModelAndView postEditNote(
            @RequestParam(value = "noteId") String id,
            @RequestParam(value = "noteTitle") String title,
            @RequestParam(value = "noteContent") String content) throws NoteNotFoundException {
        Note newNote = new Note();
        newNote.setId(Long.parseLong(id));
        newNote.setTitle(title);
        newNote.setContent(content);
        noteService.update(newNote);
        return getNotes();
    }
}

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
        return new ModelAndView("create-note");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String postCreateNote(@ModelAttribute("addedNote") Note newNote) {
        noteService.add(newNote);
        return "redirect:/note/list";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public String deleteNote(@RequestParam(value = "id") String id) throws NoteNotFoundException {
        noteService.deleteById(Long.parseLong(id));
        return "redirect:/note/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public ModelAndView getEditNote(@RequestParam(value = "id") String id) throws NoteNotFoundException {
        ModelAndView result = new ModelAndView("edit-note");
        result.addObject("note", noteService.getById(Long.parseLong(id)));
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit")
    public String postEditNote(@ModelAttribute("editedNote") Note note) throws NoteNotFoundException {
        noteService.update(note);
        return "redirect:/note/list";
    }
}

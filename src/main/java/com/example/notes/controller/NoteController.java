package com.example.notes.controller;

import com.example.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ModelAndView createNote() {
        ModelAndView result = new ModelAndView("create-note");
        return result;
    }
}

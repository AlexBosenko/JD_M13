package com.example.notes.exception;

public class NoteNotFoundException extends Exception {
    public NoteNotFoundException() {
        super("Note could not be found!");
    }

    public NoteNotFoundException(long id) {
        super(String.format("Note could not be found with id: %s", id));
    }
}

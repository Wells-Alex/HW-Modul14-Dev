package com.example.todo.service;

import com.example.todo.model.Note;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {

    private final Map<Long, Note> notes = new HashMap<>();

    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public Note add(Note note) {

        long id = new Random().nextLong();

        note.setId(id);

        notes.put(id, note);

        return note;
    }

    public void deleteById(long id) {

        if (!notes.containsKey(id)) {
            throw new NoSuchElementException("Note not found");
        }

        notes.remove(id);
    }

    public void update(Note note) {

        long id = note.getId();

        if (!notes.containsKey(id)) {
            throw new NoSuchElementException("Note not found");
        }

        Note existingNote = notes.get(id);

        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
    }

    public Note getById(long id) {

        Note note = notes.get(id);

        if (note == null) {
            throw new NoSuchElementException("Note not found");
        }

        return note;
    }
}

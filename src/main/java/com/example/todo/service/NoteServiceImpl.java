package com.example.todo.service;

import com.example.todo.exception.NoteNotFoundException;
import com.example.todo.model.Note;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteServiceImpl implements NoteService {

    private final Map<Long, Note> notes = new HashMap<>();
    private long nextId = 1;

    @Override
    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    @Override
    public Note add(Note note) {
        note.setId(nextId++);
        notes.put(note.getId(), note);
        return note;
    }

    @Override
    public void deleteById(long id) {
        if (!notes.containsKey(id)) {
            throw new NoteNotFoundException("Note not found");
        }

        notes.remove(id);
    }

    @Override
    public void update(Note note) {
        long id = note.getId();

        if (!notes.containsKey(id)) {
            throw new NoteNotFoundException("Note not found");
        }

        Note existingNote = notes.get(id);
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
    }

    @Override
    public Note getById(long id) {
        Note note = notes.get(id);

        if (note == null) {
            throw new NoteNotFoundException("Note not found");
        }

        return note;
    }

    public NoteServiceImpl() {
        add(new Note(0, "Test 1", "Content 1"));
        add(new Note(0, "Test 2", "Content 2"));
    }

}

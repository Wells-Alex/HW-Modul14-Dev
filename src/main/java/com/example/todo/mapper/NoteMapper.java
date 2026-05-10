package com.example.todo.mapper;

import com.example.todo.dto.NoteDto;
import com.example.todo.model.Note;

public class NoteMapper {

    public static Note toEntity(NoteDto dto) {
        if (dto == null) return null;

        Note note = new Note();
        note.setId(dto.getId());
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());
        return note;
    }

    public static NoteDto toDto(Note entity) {
        if (entity == null) return null;

        return new NoteDto(
                entity.getId(),
                entity.getTitle(),
                entity.getContent()
        );
    }
}

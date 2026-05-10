package com.example.todo.service;

import com.example.todo.dto.NoteDto;

import java.util.List;

public interface NoteService {

    List<NoteDto> listAll();

    NoteDto getById(Long id);

    NoteDto add(NoteDto dto);

    NoteDto update(NoteDto dto);

    void deleteById(Long id);
}

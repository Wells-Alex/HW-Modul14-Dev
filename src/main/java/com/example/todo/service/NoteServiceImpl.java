package com.example.todo.service;

import com.example.todo.dto.NoteDto;
import com.example.todo.exception.NoteNotFoundException;
import com.example.todo.mapper.NoteMapper;
import com.example.todo.model.Note;
import com.example.todo.repository.NoteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;

    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<NoteDto> listAll() {
        return repository.findAll()
                .stream()
                .map(NoteMapper::toDto)
                .toList();
    }

    @Override
    public NoteDto getById(Long id) {
        return repository.findById(id)
                .map(NoteMapper::toDto)
                .orElseThrow(() -> new NoteNotFoundException("Note not found"));
    }

    @Override
    public NoteDto add(NoteDto dto) {
        Note saved = repository.save(NoteMapper.toEntity(dto));
        return NoteMapper.toDto(saved);
    }

    @Override
    public NoteDto update(NoteDto dto) {
        if (!repository.existsById(dto.getId())) {
            throw new NoteNotFoundException("Note not found");
        }

        return NoteMapper.toDto(
                repository.save(NoteMapper.toEntity(dto))
        );
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @PostConstruct
    public void init() {
        add(new NoteDto(null, "Test 1", "Content 1"));
        add(new NoteDto(null, "Test 2", "Content 2"));
    }
}

package com.example.todo.controller;

import com.example.todo.model.Note;
import com.example.todo.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/note")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("notes", service.listAll());
        return "note-list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam long id) {
        service.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam long id, Model model) {
        model.addAttribute("note", service.getById(id));
        return "note-edit";
    }

    @PostMapping("/edit")
    public String edit(Note note) {
        service.update(note);
        return "redirect:/note/list";
    }
}

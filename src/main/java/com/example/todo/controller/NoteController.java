package com.example.todo.controller;

import com.example.todo.dto.NoteDto;
import com.example.todo.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("note", new NoteDto());
        return "note-add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("note") NoteDto dto,
                      BindingResult result) {

        if (result.hasErrors()) {
            return "note-add";
        }

        service.add(dto);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam Long id, Model model) {
        model.addAttribute("note", service.getById(id));
        return "note-edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("note") NoteDto dto,
                       BindingResult result) {

        if (result.hasErrors()) {
            return "note-edit";
        }

        service.update(dto);
        return "redirect:/note/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        service.deleteById(id);
        return "redirect:/note/list";
    }
}

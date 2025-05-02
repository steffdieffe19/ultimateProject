package com.example.demo.controller;


import com.example.demo.entity.Discente;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/discenti")

public class DiscenteController {

    @Autowired
    DiscenteService discenteService;

    // LISTA
    @GetMapping("/lista")
    public String list(Model model) {
        List<Discente> discenti = new ArrayList<>();
        discenti = discenteService.findAll();
        model.addAttribute("discenti", discenti);
        return "list-discenti";
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("discente", new Discente());
        return "form-discente";
    }

    // SALVA NUOVO
    @PostMapping
    public String create(@ModelAttribute("discente") Discente discente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-discente";
        discenteService.save(discente);
        return "redirect:/discenti/lista";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable String id, Model model) {
        model.addAttribute("discente", discenteService.get(Long.valueOf(id)));
        return "form-discente";
    }

    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable String id,
                         @ModelAttribute("discente") Discente discente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-discente";
        discente.setId(Long.valueOf(id));
        discenteService.save(discente);
        return "redirect:/discenti/lista";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        discenteService.delete(Long.valueOf(id));
        return "redirect:/discenti/lista";
    }

}

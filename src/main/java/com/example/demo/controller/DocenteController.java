package com.example.demo.controller;

import com.example.demo.entity.Docente;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    DocenteService docenteService;

    // LISTA
    @GetMapping("/lista")
    public String list(Model model) throws SQLException {
        List<Docente> docenti = new ArrayList<>();
        docenti = docenteService.findAllSortedByData_di_nascitaDesc();
        model.addAttribute("docenti", docenti);
        return "list-docenti";
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("docente", new Docente());
        return "form-docente";
    }

    // SALVA NUOVO
    @PostMapping
    public String create(@ModelAttribute("docente") Docente docente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        docenteService.save(docente);
        return "redirect:/docenti/lista";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable String id, Model model) {
        model.addAttribute("docente", docenteService.get(Long.valueOf(id)));
        return "form-docente";
    }

    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable String id,
                         @ModelAttribute("docente") Docente docente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        docente.setId(Long.valueOf(id));
        docenteService.save(docente);
        return "redirect:/docenti/lista";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable String id) {
        docenteService.delete(Long.valueOf(id));
        return "redirect:/docenti/lista";
    }


}


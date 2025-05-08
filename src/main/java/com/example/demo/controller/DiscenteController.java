package com.example.demo.controller;


import com.example.demo.entity.Discente;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/discenti")

public class DiscenteController {

    @Autowired
    DiscenteService discenteService;

    // LISTA
    @GetMapping("/lista")
    public ModelAndView list() {
        List<Discente> discenti = discenteService.findAllSortedByNome();
        ModelAndView mav = new ModelAndView("list-discenti");
        mav.addObject("discenti",discenti);
        return mav;
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public ModelAndView showAdd() {
        ModelAndView mav = new ModelAndView(("form-discente"));
        mav.addObject("discente",new Discente());
        return mav;
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
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("form-discente");
        mav.addObject("discente", discenteService.get(Long.valueOf(id)));
        return mav;
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

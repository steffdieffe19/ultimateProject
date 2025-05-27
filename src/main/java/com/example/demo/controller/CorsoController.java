package com.example.demo.controller;


import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.ArrayList;
import java.sql.SQLException;


@Controller
@RequestMapping("/corso")
public class CorsoController {

    @Autowired
    CorsoService corsoService;
    @Autowired
    private CorsoRepository corsoRepository;

    @Autowired
    private com.example.demo.repository.DiscenteRepository discenteRepository;

    // LISTA
    @GetMapping
    public ModelAndView list() throws SQLException {
        List<Corso> corsi = corsoService.findAllSortedByNome();
        ModelAndView mav = new ModelAndView("list-corsi");
        mav.addObject("corsi", corsi);
        return mav;
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public ModelAndView showAdd() {
        ModelAndView mav = new ModelAndView("form-corso");
        mav.addObject("corso", new Corso());
        mav.addObject("docenti",corsoService.getAllDocenti());
        mav.addObject("tuttiDiscenti", discenteRepository.findAll());
        return mav;
    }

    // SALVA NUOVO
    @PostMapping
    public String create(@ModelAttribute("corso") Corso corso,
                         @RequestParam(value = "discenteIds", required = false) List<Long> discenteIds,
                         BindingResult br) {
        if (br.hasErrors()) return "form-corso";

        List<Discente> discenti = discenteIds != null
                ? discenteRepository.findAllById(discenteIds)
                : new ArrayList<>();

        corso.setDiscenti(discenti);
        corsoService.save(corso);
        return "redirect:/corso";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("form-corso");
        mav.addObject("corso", corsoService.get(id));
        mav.addObject("docenti", corsoService.getAllDocenti());
        mav.addObject("tuttiDiscenti", discenteRepository.findAll());
        return mav;
    }

    // DELETE
    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        corsoService.delete(id);
        return "redirect:/corso";
    }

    // GET DISCENTI

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("corso") Corso corso,
                         @RequestParam(value = "discenteIds", required = false) List<Long> discenteIds,
                         BindingResult br) {
        if (br.hasErrors()) return "form-corso";

        List<Discente> discenti = discenteIds != null
                ? discenteRepository.findAllById(discenteIds)
                : new ArrayList<>();

        corso.setDiscenti(discenti);
        corsoService.save(corso);

        return "redirect:/corso";
    }

}
package com.example.demo.controller;


import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
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
    private CorsoService corsoService;
    @Autowired
    private DiscenteRepository discenteRepository;

    // LISTA
    @GetMapping
    public ModelAndView list() throws SQLException {
        List<CorsoDTO> corsi = corsoService.getAllCorsi();
        ModelAndView mav = new ModelAndView("list-corsi");
        mav.addObject("corsi", corsi);
        return mav;
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public ModelAndView showAdd() {
        ModelAndView mav = new ModelAndView("form-corso");
        mav.addObject("corso", new CorsoDTO());
        mav.addObject("docenti",corsoService.getAllDocenti());
        mav.addObject("tuttiDiscenti", discenteRepository.findAll());
        return mav;
    }

    // SALVA NUOVO
    @PostMapping
    public String create(@ModelAttribute("corso") CorsoDTO corsoDTO,
                         @RequestParam(value = "discenteIds", required = false) List<Long> discenteIds,
                         BindingResult br) {
        if (br.hasErrors()) return "form-corso";

        corsoDTO.setDiscentiIds(discenteIds);
        corsoService.save(corsoDTO);
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
    // GET DISCENTI

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("corso") CorsoDTO corsoDTO,
                         @RequestParam(value = "discenteIds", required = false) List<Long> discenteIds,
                         BindingResult br) {
        if (br.hasErrors()) return "form-corso";

        corsoDTO.setId(id);
        corsoDTO.setDiscentiIds(discenteIds);
        corsoService.save(corsoDTO);

        return "redirect:/corso";
    }
    // DELETE
    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        corsoService.delete(id);
        return "redirect:/corso";
    }

}
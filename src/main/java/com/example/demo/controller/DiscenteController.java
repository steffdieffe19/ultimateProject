package com.example.demo.controller;


import com.example.demo.Mapper.DiscenteMapper;
import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/discenti")

public class DiscenteController {

    private DiscenteService discenteService;
    private DiscenteMapper discenteMapper;

    @Autowired
    public DiscenteController(DiscenteService discenteService, DiscenteMapper discenteMapper) {
        this.discenteService = discenteService;
        this.discenteMapper = discenteMapper;
    }


    // LISTA
    @GetMapping("/lista")
    public ModelAndView list() {
        List<DiscenteDTO> discenteDTO = discenteService.getAllDiscenti();
        ModelAndView mav = new ModelAndView("list-discenti");
        mav.addObject("discenti",discenteDTO);
        return mav;
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public ModelAndView showAdd() {
        ModelAndView mav = new ModelAndView(("form-discente"));
        mav.addObject("discente",new DiscenteDTO());
        return mav;
    }

    // SALVA NUOVO
    @PostMapping
    public String create(@ModelAttribute("discente") DiscenteDTO discenteDTO,
                         BindingResult br) {
        if (br.hasErrors()) return "form-discente";
        System.out.println("Città di residenza nel DTO: " + discenteDTO.getCitta_di_residenza());
        discenteService.save(discenteDTO);
        return "redirect:/discenti/lista";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id) {
        DiscenteDTO discenteDTO = discenteService.get(id);
        System.out.println("Città di residenza: " + discenteDTO.getCitta_di_residenza());  // Debug log
        ModelAndView mav = new ModelAndView("form-discente");
        mav.addObject("discente", discenteService.get(id));
        return mav;
    }

    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("discente") DiscenteDTO discenteDTO,
                         BindingResult br) {
        if (br.hasErrors()) return "form-discente";
        discenteDTO.setId(id);
        discenteService.save(discenteDTO);
        return "redirect:/discenti/lista";
    }

    // DELETE
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        discenteService.delete(id);
        return "redirect:/discenti/lista";
    }

    @GetMapping("/{id}")
    public DiscenteDTO getDiscente(@PathVariable Long id) {
        return discenteService.get(id);
    }
}

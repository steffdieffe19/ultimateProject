package com.example.demo.controller;


import com.example.demo.Mapper.CorsoMapper;
import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.data.DTO.DocenteDTO;

import java.util.List;

@Controller
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
   private DocenteService docenteService;

    @Autowired
    private CorsoMapper corsoMapper;

    @Autowired
    private CorsoService corsoService;


    // LISTA
    @GetMapping("/lista")
    public String list(Model model)  {
        List<DocenteDTO> docentiDTO= docenteService.getAllDocenti();
        model.addAttribute("docenti",docentiDTO);
        return "list-docenti";
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("docente", new DocenteDTO(null, "","",null));
        return "form-docente";
    }

    // SALVA NUOVO
    @PostMapping
    public String create(@ModelAttribute("docente") DocenteDTO docenteDTO,
                         BindingResult br) {
        System.out.println("Dati ricevuti nel create: " + docenteDTO);
        if (br.hasErrors()) return "form-docente";
        docenteService.save(docenteDTO);
        return "redirect:/docenti/lista";
    }

    // FORM EDIT
    @GetMapping("/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        DocenteDTO docenteDTO= docenteService.getDocente(id);
        System.out.println("Cognome recuperato: " + docenteDTO.getCognome());
        model.addAttribute("docente", docenteDTO);
        return "form-docente";
    }

    // AGGIORNA
    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("docente") DocenteDTO docenteDTO,
                         BindingResult br) {
        if (br.hasErrors()) {
            return "form-docente";
        }
        docenteDTO.setId(id);
        docenteService.save(docenteDTO);
        System.out.println("Reindirizzamento alla lista dei docenti...");
        return "redirect:/docenti/lista";
    }


    // DELETE
    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        docenteService.delete(id);
        return "redirect:/docenti/lista";
    }


    @GetMapping("/{id}")
    public String getDocente(@PathVariable Long id, Model model) {
        DocenteDTO docenteDTO = docenteService.getDocente(id);
        List<CorsoDTO> corsi = corsoService.getCorsiByDocenteId(id);
        model.addAttribute("corsi", corsi);
        return "dettaglio-docente";
    }
}


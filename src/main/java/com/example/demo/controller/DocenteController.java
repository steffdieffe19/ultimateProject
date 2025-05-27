package com.example.demo.controller;

<<<<<<< HEAD

import com.example.demo.Mapper.CorsoMapper;
import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.service.CorsoService;
=======
import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.service.DiscenteService;
>>>>>>> Rest-Controller
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.data.DTO.DocenteDTO;

<<<<<<< HEAD
=======

>>>>>>> Rest-Controller
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/docenti")

public class DocenteController {

    @Autowired
<<<<<<< HEAD
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
=======
    private DocenteService docenteService;
    @Autowired
    private DiscenteService discenteService;

    @GetMapping
    public List<DocenteDTO> getAllDocenti() {
        return docenteService.getAllDocenti()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    private DocenteDTO toDTO(Docente d) {
        DocenteDTO dto = new DocenteDTO();
        dto.setNome(d.getNome());
        dto.setCognome(d.getCognome());
        return dto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocenteById (@PathVariable Long id) {
        Optional<Docente> docente =  docenteService.getDocenteById(id);
        return docente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/nuovo")
    public ResponseEntity<DocenteDTO> create(@RequestBody Docente docente){
        Docente saved=docenteService.createDocente(docente);
        return ResponseEntity.ok(toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> updateDocente (@PathVariable Long id, @RequestBody Docente updatedDocente) {
        Optional<Docente> existing= docenteService.getDocenteById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Docente docente = existing.get();
        docente.setNome(updatedDocente.getNome());
        docente.setCognome(updatedDocente.getCognome());

        Docente saved = docenteService.updateDocente(docente);
        return ResponseEntity.ok(toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        docenteService.delete(id);
        return ResponseEntity.noContent().build();
    }

>>>>>>> Rest-Controller
}


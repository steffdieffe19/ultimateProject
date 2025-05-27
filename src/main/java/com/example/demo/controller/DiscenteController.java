package com.example.demo.controller;


import com.example.demo.data.DTO.DiscenteDTO;
<<<<<<< HEAD
=======
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
>>>>>>> Rest-Controller
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/discenti")
@CrossOrigin(origins = "*")


public class DiscenteController {

    private final DiscenteService discenteService;

    @Autowired
<<<<<<< HEAD
    DiscenteService discenteService;

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
        mav.addObject("discente",new DiscenteDTO(null, "", "", "",0, 0));
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
=======
    public DiscenteController(DiscenteService discenteService) {
        this.discenteService = discenteService;
    }

    @GetMapping
    public List<DiscenteDTO> getAllDiscenti() {
        return discenteService.getAllDiscenti()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    private DiscenteDTO toDTO(Discente d) {
        DiscenteDTO dto = new DiscenteDTO();
        dto.setNome(d.getNome());
        dto.setCognome(d.getCognome());
        dto.setCitta_di_residenza(d.getCitta_di_residenza());
        dto.setMatricola(d.getMatricola());
        dto.setEta(d.getEta());
        return dto;
    }
    @GetMapping("/{nome}/{cognome}")
    public ResponseEntity<DiscenteDTO> getDiscenteByNomeAndCognome(@PathVariable String nome, @PathVariable String cognome) {
        Optional<Discente> discente = discenteService.getDiscenteByNomeAndCognome(nome, cognome);
        return discente.map(d -> ResponseEntity.ok(toDTO(d)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/nuovo")
    public ResponseEntity<DiscenteDTO> createDiscente(@RequestBody Discente discente) {
        Discente saved = discenteService.createDiscente(discente);
        return ResponseEntity.ok(toDTO(saved));
>>>>>>> Rest-Controller
    }
    @PutMapping("/{id}")
    public ResponseEntity<DiscenteDTO> updateDiscente(
            @PathVariable Long id,
            @RequestBody Discente updatedDiscente) {
        Optional<Discente> existing = discenteService.getDiscenteById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Discente discente = existing.get();
        discente.setNome(updatedDiscente.getNome());
        discente.setCognome(updatedDiscente.getCognome());
        discente.setMatricola(updatedDiscente.getMatricola());
        discente.setCitta_di_residenza(updatedDiscente.getCitta_di_residenza());
        discente.setEta(updatedDiscente.getEta());

        Discente saved = discenteService.updateDiscente(discente);
        return ResponseEntity.ok(toDTO(saved));
    }
    // DELETE
<<<<<<< HEAD
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        discenteService.delete(id);
        return "redirect:/discenti/lista";
    }

    @GetMapping("/{id}")
    public DiscenteDTO getDiscente(@PathVariable Long id) {
        return discenteService.get(id);
=======
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscente(@PathVariable Long id) {
        discenteService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id_discente}/corsi/{id_corso}")
    public ResponseEntity<DiscenteDTO> aggiungiCorso(
            @PathVariable Long id_discente,
            @PathVariable Long id_corso) {
        Discente updated = discenteService.aggiungiCorso(id_discente, id_corso);
        return ResponseEntity.ok(toDTO(updated));
    }

    @DeleteMapping("/{id_discente}/corsi/{id_corso}")
    public ResponseEntity<DiscenteDTO> rimuoviCorso(
            @PathVariable Long id_discente,
            @PathVariable Long id_corso) {
        Discente updated = discenteService.rimuoviCorso(id_discente, id_corso);
        return ResponseEntity.ok(toDTO(updated));
    }
    @GetMapping("/{id_discente}/corsi")
    public ResponseEntity<List<Corso>> getCorsiDiscente (
            @PathVariable Long id_discente) {
        return ResponseEntity.ok(discenteService.getCorsiDiscente(id_discente));
>>>>>>> Rest-Controller
    }
}

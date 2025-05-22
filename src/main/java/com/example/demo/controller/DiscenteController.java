package com.example.demo.controller;


import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
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
    public DiscenteController(DiscenteService discenteService) {
        this.discenteService = discenteService;
    }

    @GetMapping
    public List<Discente> getAllDiscenti() {
        return discenteService.getAllDiscenti();
    }
    @GetMapping("/{matricola}")
    public ResponseEntity<Discente> getDiscenteByMatricola(@PathVariable Integer matricola) {
        Optional<Discente> discente = discenteService.getDiscenteByMatricola(matricola);
        return discente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public Discente createDiscente(@RequestBody Discente discente){
        return discenteService.createDiscente(discente);
    }
    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscente (@PathVariable Long id){
        discenteService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/{id_discente}/corsi/{id_corso}")
    public ResponseEntity<Discente> aggiungiCorso (
            @PathVariable Long id_discente,
            @PathVariable Long id_corso) {
        return ResponseEntity.ok(discenteService.aggiungiCorso(id_discente, id_corso));
    }
    @DeleteMapping("/{id_discente}/corsi/{id_corso}")
    public ResponseEntity<Discente> rimuoviCorso (
            @PathVariable Long id_discente,
            @PathVariable Long id_corso) {
        return ResponseEntity.ok(discenteService.rimuoviCorso(id_discente, id_corso));
    }
    @GetMapping("/{id_discente}/corsi")
    public ResponseEntity<List<Corso>> getCorsiDiscente (
            @PathVariable Long id_discente) {
        return ResponseEntity.ok(discenteService.getCorsiDiscente(id_discente));
    }
}

package com.example.demo.controller;

import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corsi")
@CrossOrigin(origins = "*")
public class CorsoController {

    private final CorsoService corsoService;

    @Autowired
    public CorsoController(CorsoService corsoService) {
        this.corsoService = corsoService;
    }

    @GetMapping
    public ResponseEntity<List<Corso>> getAllCorsi() {
        return ResponseEntity.ok(corsoService.getAllCorsi());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Corso> getCorsoById(@PathVariable Long id) {
        return corsoService.getCorsoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Corso> createCorso(@RequestBody Corso corso) {
        return ResponseEntity.ok(corsoService.createCorso(corso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Corso> updateCorso(
            @PathVariable Long id,
            @RequestBody Corso corso) {
        return ResponseEntity.ok(corsoService.updateCorso(id, corso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCorso(@PathVariable Long id) {
        corsoService.deleteCorso(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{corsoId}/discenti/{discenteId}")
    public ResponseEntity<Corso> aggiungiDiscente(
            @PathVariable Long corsoId,
            @PathVariable Long discenteId) {
        return ResponseEntity.ok(corsoService.aggiungiDiscente(corsoId, discenteId));
    }

    @DeleteMapping("/{corsoId}/discenti/{discenteId}")
    public ResponseEntity<Corso> rimuoviDiscente(
            @PathVariable Long corsoId,
            @PathVariable Long discenteId) {
        return ResponseEntity.ok(corsoService.rimuoviDiscente(corsoId, discenteId));
    }

    @GetMapping("/{corsoId}/discenti")
    public ResponseEntity<List<Discente>> getDiscentiCorso(@PathVariable Long corsoId) {
        return ResponseEntity.ok(corsoService.getDiscentiCorso(corsoId));
    }
}
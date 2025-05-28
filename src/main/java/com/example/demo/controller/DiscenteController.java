package com.example.demo.controller;

import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discenti")
@CrossOrigin(origins = "*")
public class DiscenteController {

    private final DiscenteService discenteService;

    @Autowired
    public DiscenteController(DiscenteService discenteService) {
        this.discenteService = discenteService;
    }

    @GetMapping
    public List<DiscenteDTO> getAllDiscenti() {
        return discenteService.getAllDiscenti();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiscenteDTO> getDiscente(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(discenteService.get(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DiscenteDTO> createDiscente(@RequestBody DiscenteDTO discenteDTO) {
        DiscenteDTO saved = discenteService.save(discenteDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiscenteDTO> updateDiscente(@PathVariable Long id, @RequestBody DiscenteDTO discenteDTO) {
        discenteDTO.setId(id);
        try {
            DiscenteDTO updated = discenteService.update(discenteDTO);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiscente(@PathVariable Long id) {
        try {
            discenteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{idDiscente}/corsi/{idCorso}")
    public ResponseEntity<DiscenteDTO> aggiungiCorso(@PathVariable Long idDiscente, @PathVariable Long idCorso) {
        try {
            DiscenteDTO updated = discenteService.aggiungiCorso(idDiscente, idCorso);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idDiscente}/corsi/{idCorso}")
    public ResponseEntity<DiscenteDTO> rimuoviCorso(@PathVariable Long idDiscente, @PathVariable Long idCorso) {
        try {
            DiscenteDTO updated = discenteService.rimuoviCorso(idDiscente, idCorso);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idDiscente}/corsi")
    public ResponseEntity<List<Corso>> getCorsiDiscente(@PathVariable Long idDiscente) {
        try {
            List<Corso> corsi = discenteService.getCorsiDiscente(idDiscente);
            return ResponseEntity.ok(corsi);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
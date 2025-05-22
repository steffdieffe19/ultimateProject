package com.example.demo.controller;

import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.service.DiscenteService;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/docenti")

public class DocenteController {

    @Autowired
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

}


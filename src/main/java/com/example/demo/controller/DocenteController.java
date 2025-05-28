package com.example.demo.controller;

import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docenti")
@CrossOrigin(origins = "*")
public class DocenteController {

    private final DocenteService docenteService;

    @Autowired
    public DocenteController(DocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping
    public List<DocenteDTO> getAllDocenti() {
        return docenteService.getAllDocenti();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocenteDTO> getDocente(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(docenteService.getDocente(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DocenteDTO> createDocente(@RequestBody DocenteDTO docenteDTO) {
        DocenteDTO saved = docenteService.save(docenteDTO);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocenteDTO> updateDocente(@PathVariable Long id, @RequestBody DocenteDTO docenteDTO) {
        docenteDTO.setId(id);
        try {
            DocenteDTO updated = docenteService.update(docenteDTO);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable Long id) {
        try {
            docenteService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
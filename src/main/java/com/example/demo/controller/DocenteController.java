package com.example.demo.controller;

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
    public List<Docente> getAllDocenti() {
        return docenteService.getAllDocenti();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> getDocenteById (@PathVariable Long id) {
        Optional<Docente> docente =  docenteService.getDocenteById(id);
        return docente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Docente create(@RequestBody Docente docente){
        return docenteService.createDocente(docente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente (@PathVariable Long id) {
        discenteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}


package com.example.demo.service;

import com.example.demo.data.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

   private final DocenteRepository docenteRepository;

    @Autowired
    public DocenteService(DocenteRepository docenteRepository){

        this.docenteRepository=docenteRepository;
    }
    public List<Docente> getAllDocenti()  {
        return docenteRepository.findAll();
    }

    public Optional<Docente> getDocenteById(Long id) {
        return docenteRepository.findById(id);
    }

    public Docente createDocente(Docente docente) {
        return docenteRepository.save(docente);
    }

    public Docente updateDocente(Docente docente) {
        Docente existing = docenteRepository.findById(docente.getId())
                .orElseThrow(() -> new RuntimeException("Docente non trovato con id: " + docente.getId()));

        existing.setNome(docente.getNome());
        existing.setCognome(docente.getCognome());

        return docenteRepository.save(existing);
    }

    public void delete(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente non trovato"));
        docenteRepository.delete(docente);
    }


}

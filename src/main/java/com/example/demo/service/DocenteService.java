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

    public void delete(Long id) {

        if (docenteRepository.existsById(id)) {
            docenteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Docente non trovato" + id);
        }
    }
}

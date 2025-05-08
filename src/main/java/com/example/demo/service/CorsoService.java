package com.example.demo.service;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.sql.SQLException;

@Service

public class CorsoService {
    @Autowired
    CorsoRepository corsoRepository;

    public List<Corso> findAllSortedByNome() throws SQLException {
        return corsoRepository.findAllSortedByNome();
    }
    public Corso get(Long id) {
        return corsoRepository.findById(id).orElseThrow();
    }
    public void save(Corso c) {corsoRepository.save(c);}

    public void delete(Long id) {corsoRepository.deleteById(id);}

    @Autowired
    private DocenteRepository docenteRepository;

    public List<Docente> getAllDocenti() {
        return docenteRepository.findAll();
    }
}

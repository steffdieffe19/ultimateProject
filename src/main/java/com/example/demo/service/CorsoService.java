package com.example.demo.service;

import com.example.demo.Mapper.CorsoMapper;
import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.sql.SQLException;
import java.util.stream.Collectors;

@Service

public class CorsoService {
    @Autowired
    CorsoRepository corsoRepository;

    @Autowired
    private CorsoMapper corsoMapper;

    @Autowired
    private DocenteRepository docenteRepository;

    public List<Corso> findAllSortedByNome() throws SQLException {
        return corsoRepository.findAllSortedByNome();
    }

    public Corso get(Long id) {
        return corsoRepository.findById(id).orElseThrow();
    }

    public void save(Corso c) {
        corsoRepository.save(c);
    }

    public void delete(Long id) {
        corsoRepository.deleteById(id);
    }

    public List<Docente> getAllDocenti() {
        return docenteRepository.findAll();
    }

    public List<CorsoDTO> getCorsiByDocenteId(Long docenteId) {
        List<Corso> corsi = corsoRepository.findByDocenteId(docenteId);
        return corsi.stream()
                .map(corsoMapper::toDto)
                .collect(Collectors.toList());
    }

}

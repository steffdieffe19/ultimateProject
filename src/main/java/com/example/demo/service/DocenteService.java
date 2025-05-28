package com.example.demo.service;

import com.example.demo.Mapper.DocenteMapper;
import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.data.entity.Docente;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocenteService {

    private final DocenteRepository docenteRepository;
    private final DocenteMapper docenteMapper;

    @Autowired
    public DocenteService(DocenteRepository docenteRepository, DocenteMapper docenteMapper) {
        this.docenteRepository = docenteRepository;
        this.docenteMapper = docenteMapper;
    }

    public List<DocenteDTO> getAllDocenti() {
        List<Docente> docenti = docenteRepository.findAll();
        return docenteMapper.toDTOList(docenti);
    }

    public DocenteDTO getDocente(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente non trovato"));
        return docenteMapper.toDTO(docente);
    }

    public DocenteDTO save(DocenteDTO docenteDTO) {
        Docente docente = docenteMapper.toEntity(docenteDTO);
        docenteRepository.save(docente);
        return docenteMapper.toDTO(docente);
    }

    public DocenteDTO update(DocenteDTO docenteDTO) {
        Docente existing = docenteRepository.findById(docenteDTO.getId())
                .orElseThrow(() -> new RuntimeException("Docente non trovato con id: " + docenteDTO.getId()));

        existing.setNome(docenteDTO.getNome());
        existing.setCognome(docenteDTO.getCognome());
        existing.setData_di_nascita(docenteDTO.getData_di_nascita());

        docenteRepository.save(existing);
        return docenteMapper.toDTO(existing);
    }

    public void delete(Long id) {
        Docente docente = docenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Docente non trovato"));
        docenteRepository.delete(docente);
    }
}
package com.example.demo.service;

import com.example.demo.Mapper.DiscenteMapper;
import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscenteService {

    private final DiscenteRepository discenteRepository;
    private final CorsoRepository corsoRepository;
    private final DiscenteMapper discenteMapper;

    @Autowired
    public DiscenteService(DiscenteRepository discenteRepository,
                           CorsoRepository corsoRepository,
                           DiscenteMapper discenteMapper) {
        this.discenteRepository = discenteRepository;
        this.corsoRepository = corsoRepository;
        this.discenteMapper = discenteMapper;
    }

    public List<DiscenteDTO> getAllDiscenti() {
        List<Discente> discenti = discenteRepository.findAll();
        return discenteMapper.toDTOList(discenti);
    }

    public DiscenteDTO get(Long id) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discente non trovato"));
        return discenteMapper.toDTO(discente);
    }

    public DiscenteDTO save(DiscenteDTO discenteDTO) {
        Discente discente = discenteMapper.toEntity(discenteDTO);

        if (discente.getCitta_di_residenza() == null || discente.getCitta_di_residenza().isEmpty()) {
            discente.setCitta_di_residenza("Città sconosciuta");
        }

        if (discenteDTO.getCorsiId() != null) {
            List<Corso> corsi = corsoRepository.findAllById(discenteDTO.getCorsiId());
            discente.setCorsi(corsi);
        }

        discenteRepository.save(discente);
        return discenteMapper.toDTO(discente);
    }

    public DiscenteDTO update(DiscenteDTO discenteDTO) {
        Discente existing = discenteRepository.findById(discenteDTO.getId())
                .orElseThrow(() -> new RuntimeException("Discente non trovato con id: " + discenteDTO.getId()));

        existing.setNome(discenteDTO.getNome());
        existing.setCognome(discenteDTO.getCognome());
        existing.setCitta_di_residenza(discenteDTO.getCitta_di_residenza());
        existing.setMatricola(discenteDTO.getMatricola());
        existing.setEta(discenteDTO.getEta());

        if (discenteDTO.getCorsiId() != null) {
            List<Corso> corsi = corsoRepository.findAllById(discenteDTO.getCorsiId());
            existing.setCorsi(corsi);
        }

        discenteRepository.save(existing);
        return discenteMapper.toDTO(existing);
    }

    public void delete(Long id) {
        Discente discente = discenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Discente non trovato"));
        discenteRepository.delete(discente);
    }

    public DiscenteDTO aggiungiCorso(Long idDiscente, Long idCorso) {
        Discente discente = discenteRepository.findById(idDiscente)
                .orElseThrow(() -> new RuntimeException("Discente non trovato: " + idDiscente));
        Corso corso = corsoRepository.findById(idCorso)
                .orElseThrow(() -> new RuntimeException("Corso non trovato: " + idCorso));

        if (!discente.getCorsi().contains(corso)) {
            discente.getCorsi().add(corso);
            corso.getDiscenti().add(discente);
        }

        discenteRepository.save(discente);
        return discenteMapper.toDTO(discente);
    }

    public DiscenteDTO rimuoviCorso(Long idDiscente, Long idCorso) {
        Discente discente = discenteRepository.findById(idDiscente)
                .orElseThrow(() -> new RuntimeException("Discente non trovato: " + idDiscente));
        Corso corso = corsoRepository.findById(idCorso)
                .orElseThrow(() -> new RuntimeException("Corso non trovato: " + idCorso));

        discente.getCorsi().remove(corso);
        corso.getDiscenti().remove(discente);

        discenteRepository.save(discente);
        return discenteMapper.toDTO(discente);
    }

    public List<Corso> getCorsiDiscente(Long idDiscente) {
        Discente discente = discenteRepository.findById(idDiscente)
                .orElseThrow(() -> new RuntimeException("Discente non trovato: " + idDiscente));
        return discente.getCorsi();
    }
}
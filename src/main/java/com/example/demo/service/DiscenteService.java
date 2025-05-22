package com.example.demo.service;


import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscenteService {

    private final DiscenteRepository discenteRepository;
    private final CorsoRepository corsoRepository;

    @Autowired
    public DiscenteService(DiscenteRepository discenteRepository, CorsoRepository corsoRepository) {
        this.discenteRepository = discenteRepository;
        this.corsoRepository = corsoRepository;
    }

    public List<Discente> getAllDiscenti() {
        return discenteRepository.findAll();
    }

    public Optional<Discente> getDiscenteByMatricola(Integer matricola) {
        return discenteRepository.findByMatricola(matricola);
    }

    public Discente createDiscente(Discente discente) {
        return discenteRepository.save(discente);
    }

    public void delete(Long id) {
        if (discenteRepository.existsById(id)) {
            discenteRepository.deleteById(id);
        } else {
            throw new RuntimeException("Discente non trovato" + id);
        }
    }
    public Discente aggiungiCorso (Long id_discente, Long id_corso) {
        Discente discente = discenteRepository.findById(id_discente)
                .orElseThrow(() -> new RuntimeException("Discente non trovato" + id_discente));
        Corso corso = corsoRepository.findById(id_corso)
                .orElseThrow(() -> new RuntimeException("Corso non trovato" + id_corso));

        corso.getDiscenti().add(discente);
        discente.getCorsi().add(corso);

        return discenteRepository.save(discente);
    }

    public List<Corso> getCorsiDiscente (Long id_discente) {
        return discenteRepository.findById(id_discente)
                .map(Discente::getCorsi)
                .orElseThrow(() -> new RuntimeException("Corso non trovato" + id_discente));

    }


    public Discente rimuoviCorso(Long discenteId, Long corsoId) {
        Discente discente = discenteRepository.findById(discenteId)
                .orElseThrow(() -> new RuntimeException("Discente non trovato con id: " + discenteId));

        Corso corso = corsoRepository.findById(corsoId)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con id: " + corsoId));

        discente.getCorsi().remove(corso);
        corso.getDiscenti().remove(discente);

        return discenteRepository.save(discente);
    }

}

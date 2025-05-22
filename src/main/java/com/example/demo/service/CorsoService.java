package com.example.demo.service;

import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Docente;
import com.example.demo.data.entity.Discente;
import com.example.demo.repository.CorsoRepository;
import com.example.demo.repository.DiscenteRepository;
import com.example.demo.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CorsoService {

    private final CorsoRepository corsoRepository;
    private final DiscenteRepository discenteRepository;
    private final DocenteRepository docenteRepository;

    @Autowired
    public CorsoService(CorsoRepository corsoRepository, 
                       DiscenteRepository discenteRepository,
                       DocenteRepository docenteRepository) {
        this.corsoRepository = corsoRepository;
        this.discenteRepository = discenteRepository;
        this.docenteRepository = docenteRepository;
    }

    @Transactional(readOnly = true)
    public List<Corso> getAllCorsi() {
        return corsoRepository.findAllWithDetails();
    }

    @Transactional(readOnly = true)
    public List<Corso> getCorsiByDocente(Long docenteId) {
        return corsoRepository.findByDocenteId(docenteId);
    }

    @Transactional(readOnly = true)
    public Optional<Corso> getCorsoById(Long id) {
        return corsoRepository.findById(id);
    }

    @Transactional
    public Corso createCorso(Corso corso) {
        validateCorso(corso);
        // Verifica che il docente esista
        Docente docente = docenteRepository.findById(corso.getDocente().getId())
                .orElseThrow(() -> new RuntimeException("Docente non trovato con id: " + corso.getDocente().getId()));
        corso.setDocente(docente);
        return corsoRepository.save(corso);
    }

    @Transactional
    public Corso updateCorso(Long id, Corso corsoDetails) {
        Corso corso = corsoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con id: " + id));

        validateCorso(corsoDetails);

        // Verifica che il nuovo docente esista se viene modificato
        if (corsoDetails.getDocente() != null && 
            !corsoDetails.getDocente().getId().equals(corso.getDocente().getId())) {
            Docente nuovoDocente = docenteRepository.findById(corsoDetails.getDocente().getId())
                    .orElseThrow(() -> new RuntimeException("Docente non trovato con id: " + 
                                corsoDetails.getDocente().getId()));
            corso.setDocente(nuovoDocente);
        }

        corso.setNome(corsoDetails.getNome());
        corso.setAnno_accademico(corsoDetails.getAnno_accademico());

        return corsoRepository.save(corso);
    }

    @Transactional
    public void deleteCorso(Long id) {
        Corso corso = corsoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con id: " + id));

        // Rimuovi le associazioni con i discenti
        corso.getDiscenti().forEach(discente -> {
            discente.getCorsi().remove(corso);
        });
        corso.getDiscenti().clear();

        corsoRepository.delete(corso);
    }

    @Transactional
    public Corso aggiungiDiscente(Long corsoId, Long discenteId) {
        Corso corso = corsoRepository.findById(corsoId)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con id: " + corsoId));

        Discente discente = discenteRepository.findById(discenteId)
                .orElseThrow(() -> new RuntimeException("Discente non trovato con id: " + discenteId));

        if (!corso.getDiscenti().contains(discente)) {
            corso.getDiscenti().add(discente);
            discente.getCorsi().add(corso);
        }

        return corsoRepository.save(corso);
    }

    @Transactional
    public Corso rimuoviDiscente(Long corsoId, Long discenteId) {
        Corso corso = corsoRepository.findById(corsoId)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con id: " + corsoId));

        Discente discente = discenteRepository.findById(discenteId)
                .orElseThrow(() -> new RuntimeException("Discente non trovato con id: " + discenteId));

        corso.getDiscenti().remove(discente);
        discente.getCorsi().remove(corso);

        return corsoRepository.save(corso);
    }

    @Transactional(readOnly = true)
    public List<Corso> findCorsiByNome(String nome) {
        return corsoRepository.findByNomeContainingIgnoreCase(nome);
    }

    private void validateCorso(Corso corso) {
        if (corso.getNome() == null || corso.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Il nome del corso non può essere vuoto");
        }
        if (corso.getAnno_accademico() == null) {
            throw new IllegalArgumentException("L'anno accademico non può essere vuoto");
        }
        if (corso.getDocente() == null || corso.getDocente().getId() == null) {
            throw new IllegalArgumentException("Il docente è obbligatorio");
        }
    }

    @Transactional(readOnly = true)
    public List<Discente> getDiscentiCorso(Long corsoId) {
        return corsoRepository.findById(corsoId)
                .map(Corso::getDiscenti)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con id: " + corsoId));
    }
}
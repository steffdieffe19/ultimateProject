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
    public List<Corso> getCorsiByDocente(String nome, String cognome) {
        return corsoRepository.findByDocenteNomeAndCognome(nome, cognome);
    }

    @Transactional(readOnly = true)
    public Optional<Corso> getCorsoById(Long id) {
        return corsoRepository.findById(id);
    }

    @Transactional
    public Corso createCorso(Corso corso) {
        validateCorso(corso);

        Docente docente = corso.getDocente();
        if (docente.getData_di_nascita() == null) {
            throw new IllegalArgumentException("Il campo 'data_di_nascita' del docente è obbligatorio.");
        }

        Docente docenteEsistente = docenteRepository.findByNomeAndCognome(
                        docente.getNome(), docente.getCognome())
                .orElseGet(() -> docenteRepository.save(docente));
        corso.setDocente(docenteEsistente);

        if (corso.getDiscenti() != null && !corso.getDiscenti().isEmpty()) {
            List<Discente> discentiAssociati = corso.getDiscenti().stream()
                    .map(d -> discenteRepository.findByNomeAndCognome(d.getNome(), d.getCognome())
                            .orElseGet(() -> discenteRepository.save(d)))
                    .toList();
            corso.setDiscenti(discentiAssociati);
        }

        return corsoRepository.save(corso);
    }

    @Transactional
    public Corso updateCorso(Corso corso) {
        Corso existing = corsoRepository.findById(corso.getId())
                .orElseThrow(() -> new RuntimeException("Corso non trovato con id: " + corso.getId()));

        validateCorso(corso);

        Docente docenteEsistente = null;
        if (corso.getDocente() != null) {
            docenteEsistente = docenteRepository.findByNomeAndCognome(
                            corso.getDocente().getNome(),
                            corso.getDocente().getCognome())
                    .orElseGet(() -> docenteRepository.save(corso.getDocente()));
            corso.setDocente(docenteEsistente);
        }

        existing.setNome(corso.getNome());
        existing.setAnno_accademico(corso.getAnno_accademico());
        existing.setDocente(docenteEsistente);

        return corsoRepository.save(existing);
    }

    @Transactional
    public void deleteCorso(Long id) {
        Corso corso = corsoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con id: " + id));

        corso.getDiscenti().forEach(discente -> discente.getCorsi().remove(corso));
        corso.getDiscenti().clear();

        corsoRepository.delete(corso);
    }

    @Transactional
    public Corso aggiungiDiscente(Long corsoId, String nomeDiscente, String cognomeDiscente) {
        Corso corso = corsoRepository.findById(corsoId)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con id: " + corsoId));

        Discente discente = discenteRepository.findByNomeAndCognome(nomeDiscente, cognomeDiscente)
                .orElseThrow(() -> new RuntimeException(
                        "Discente non trovato: " + nomeDiscente + " " + cognomeDiscente));

        if (!corso.getDiscenti().contains(discente)) {
            corso.getDiscenti().add(discente);
            discente.getCorsi().add(corso);
        }

        return corsoRepository.save(corso);
    }

    @Transactional
    public Corso rimuoviDiscente(Long corsoId, String nomeDiscente, String cognomeDiscente) {
        Corso corso = corsoRepository.findById(corsoId)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con id: " + corsoId));

        Discente discente = discenteRepository.findByNomeAndCognome(nomeDiscente, cognomeDiscente)
                .orElseThrow(() -> new RuntimeException(
                        "Discente non trovato: " + nomeDiscente + " " + cognomeDiscente));

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
        if (corso.getDocente() == null ||
                corso.getDocente().getNome() == null ||
                corso.getDocente().getCognome() == null) {
            throw new IllegalArgumentException("Il docente è obbligatorio");
        }
    }

    @Transactional(readOnly = true)
    public List<Discente> getDiscentiCorso(Long corsoId) {
        return corsoRepository.findById(corsoId)
                .map(Corso::getDiscenti)
                .orElseThrow(() -> new RuntimeException("Corso non trovato con id: " + corsoId));
    }

    @Transactional(readOnly = true)
    public Optional<Docente> getDocenteByNomeAndCognome(String nome, String cognome) {
        return docenteRepository.findByNomeAndCognome(nome, cognome);
    }
}
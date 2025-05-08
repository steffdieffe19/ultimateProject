package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "corso")

public class Corso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer anno_accademico;

    @ManyToOne
    @JoinColumn(name = "id_docente", nullable = false)
    private Docente docente;

    @ManyToMany
    @JoinTable(
            name = "corso_discente",
            joinColumns = @JoinColumn(name = "id_corso"),
            inverseJoinColumns = @JoinColumn(name = "id_discente")
    )
    private List<Discente> discenti = new ArrayList<>();

    public List<Discente> getDiscenti() {
        return discenti;
    }

    public void setDiscenti(List<Discente> discenti) {
        this.discenti = discenti;
    }

    //COSTRUTTORI
    public Corso() {
    }

    public Corso(String nome, Integer anno_accademico, Docente docente) {
        this.nome = nome;
        this.anno_accademico = anno_accademico;
        this.docente = docente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnno_accademico() {
        return anno_accademico;
    }

    public void setAnno_accademico(Integer anno_accademico) {
        this.anno_accademico = anno_accademico;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
}

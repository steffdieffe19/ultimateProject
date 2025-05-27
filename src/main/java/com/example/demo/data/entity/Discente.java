package com.example.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "discenti")
public class Discente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false)
    private String citta_di_residenza;

    @Column(nullable = false, unique = true)
    private Integer matricola;

    @Column(nullable = false)
    private Integer eta;

    @ManyToMany(mappedBy = "discenti")
    private List<Corso> corsi=new ArrayList<>();
    public List<Corso> getCorsi() {
        return corsi;
    }
    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }

    /* costruttori */
    public Discente() {}
    public Discente(String nome, String cognome, String citta_di_residenza, Integer matricola, Integer eta) {
        this.nome = nome;
        this.cognome = cognome;
        this.citta_di_residenza = citta_di_residenza;
        this.matricola = matricola;
        this.eta = eta;
    }
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getCognome() {return cognome;}

    public void setCognome(String cognome) {this.cognome = cognome;}

    public String getCitta_di_residenza() {return citta_di_residenza;}

    public void setCitta_di_residenza(String citta_di_residenza) {this.citta_di_residenza = citta_di_residenza;}

    public Integer getMatricola() {return matricola;}

    public void setMatricola(Integer matricola) {this.matricola = matricola;}

    public Integer getEta() {return eta;}

    public void setEta(Integer eta) {this.eta = eta;}


}

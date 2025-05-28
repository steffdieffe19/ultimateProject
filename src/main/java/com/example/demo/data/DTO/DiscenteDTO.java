package com.example.demo.data.DTO;

import java.util.List;

public class DiscenteDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String citta_di_residenza;
    private Integer matricola;
    private Integer eta;
    private List<Long> corsiId;

    // Costruttori
    public DiscenteDTO() {}

    public DiscenteDTO(Long id, String nome, String cognome, String citta_di_residenza, Integer matricola, Integer eta, List<Long> corsiId) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.citta_di_residenza = citta_di_residenza;
        this.matricola = matricola;
        this.eta = eta;
        this.corsiId = corsiId;
    }

    // Getter e Setter
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCitta_di_residenza() {
        return citta_di_residenza;
    }

    public void setCitta_di_residenza(String citta_di_residenza) {
        this.citta_di_residenza = citta_di_residenza;
    }

    public Integer getMatricola() {
        return matricola;
    }

    public void setMatricola(Integer matricola) {
        this.matricola = matricola;
    }

    public Integer getEta() {
        return eta;
    }

    public void setEta(Integer eta) {
        this.eta = eta;
    }

    public List<Long> getCorsiId() {
        return corsiId;
    }

    public void setCorsiId(List<Long> corsiId) {
        this.corsiId = corsiId;
    }
}
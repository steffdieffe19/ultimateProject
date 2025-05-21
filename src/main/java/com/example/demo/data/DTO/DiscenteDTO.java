package com.example.demo.data.DTO;

import java.util.ArrayList;
import java.util.List;

public class DiscenteDTO {
    private Long id;
    private String nome;
    private String cognome;
    private String citta_di_residenza;
    private Integer matricola;
    private Integer eta;
    private List<Long> corsiIds= new ArrayList<>();


    public DiscenteDTO() {

    }

    //COSTRUTTORI
    public DiscenteDTO(Long id, String nome, String cognome,String citta_di_residenza , Integer matricola, Integer eta,List<Long> corsiIds) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.citta_di_residenza = citta_di_residenza;
        this.matricola = matricola;
        this.eta = eta;
        this.corsiIds = corsiIds;
    }

    //Getter e Setter
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

    public List<Long> getCorsiIds() {return corsiIds;}

    public void setCorsiIds(List<Long> corsiIds) {this.corsiIds = corsiIds;}

}



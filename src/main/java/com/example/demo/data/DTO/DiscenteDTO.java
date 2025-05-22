package com.example.demo.data.DTO;

import java.util.List;

public class DiscenteDTO {

    private String nome;
    private String cognome;
    private String citta_di_residenza;
    private Integer matricola;
    private Integer eta;



    //COSTRUTTORI
    public DiscenteDTO( String nome, String cognome,String citta_di_residenza , Integer matricola, Integer eta) {

        this.nome = nome;
        this.cognome = cognome;
        this.citta_di_residenza = citta_di_residenza;
        this.matricola = matricola;
        this.eta = eta;
    }

    public DiscenteDTO() {

    }

    //Getter e Setter


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



}




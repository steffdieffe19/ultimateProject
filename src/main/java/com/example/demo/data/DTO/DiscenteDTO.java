package com.example.demo.data.DTO;

import java.util.List;

public class DiscenteDTO {
<<<<<<< HEAD
    private Long id;
=======

>>>>>>> Rest-Controller
    private String nome;
    private String cognome;
    private String citta_di_residenza;
    private Integer matricola;
    private Integer eta;
<<<<<<< HEAD
    private List<Long> corsiId;


    //COSTRUTTORI
    public DiscenteDTO(Long id, String nome, String cognome,String citta_di_residenza , Integer matricola, Integer eta) {
        this.id = id;
=======



    //COSTRUTTORI
    public DiscenteDTO( String nome, String cognome,String citta_di_residenza , Integer matricola, Integer eta) {

>>>>>>> Rest-Controller
        this.nome = nome;
        this.cognome = cognome;
        this.citta_di_residenza = citta_di_residenza;
        this.matricola = matricola;
        this.eta = eta;
    }

    public DiscenteDTO() {

    }

    //Getter e Setter
<<<<<<< HEAD
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
=======

>>>>>>> Rest-Controller

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

<<<<<<< HEAD
    public List<Long> getCorsiId() {return corsiId;}
    public void setCorsiId(List<Long> corsiId) {this.corsiId = corsiId;}
=======
>>>>>>> Rest-Controller


}


<<<<<<< HEAD
=======

>>>>>>> Rest-Controller

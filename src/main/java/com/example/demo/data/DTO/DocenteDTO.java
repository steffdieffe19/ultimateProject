package com.example.demo.data.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class DocenteDTO {
    private Long id;
    private String nome;
    private String cognome;
    private List<Long> corsiId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_di_nascita;


    //costruttore
    public DocenteDTO (Long id, String nome,String cognome, Date data_di_nascita) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.data_di_nascita= data_di_nascita;
    }

    public DocenteDTO() {

    }

    //getter e setter
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCognome() {return cognome;}
    public void setCognome(String cognome) {this.cognome = cognome;}

    public Date getData_di_nascita() {return data_di_nascita;}
    public void setData_di_nascita(Date data_di_nascita) {this.data_di_nascita = data_di_nascita;}

    public List<Long> getCorsiId() {return corsiId;}
    public void setCorsiId(List<Long> corsiId) {this.corsiId = corsiId;}
}

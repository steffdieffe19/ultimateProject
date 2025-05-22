package com.example.demo.data.DTO;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class DocenteDTO {

    private String nome;
    private String cognome;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data_di_nascita;


    //costruttore
    public DocenteDTO ( String nome,String cognome) {
        this.nome = nome;
        this.cognome = cognome;

    }

    public DocenteDTO() {

    }

    //getter e setter

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getCognome() {return cognome;}
    public void setCognome(String cognome) {this.cognome = cognome;}


}

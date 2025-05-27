package com.example.demo.data.DTO;

public class DiscenteLiteDTO {
    private String nome;
    private String cognome;

    public DiscenteLiteDTO(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    public DiscenteLiteDTO() {
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
}

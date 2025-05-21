package com.example.demo.data.DTO;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;

public class DocenteDTO {
    private Long id;
    private String nome;
    private String cognome;
    private List<Long> corsiId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataDiNascita;  // Rinominato secondo le convenzioni Java

    public DocenteDTO(Long id, String nome, String cognome, LocalDate dataDiNascita) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
    }

    public DocenteDTO() {
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

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public List<Long> getCorsiId() {
        return corsiId;
    }

    public void setCorsiId(List<Long> corsiId) {
        this.corsiId = corsiId;
    }
}
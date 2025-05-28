package com.example.demo.data.DTO;

import java.util.List;

public class CorsoDTO {
    private Long id;
    private String nome;
    private Integer anno_accademico;
    private DocenteLiteDTO docente;
    private List<DiscenteLiteDTO> discenti;

    public CorsoDTO() {}

    public CorsoDTO(Long id, String nome, Integer anno_accademico, DocenteLiteDTO docente, List<DiscenteLiteDTO> discenti) {
        this.id = id;
        this.nome = nome;
        this.anno_accademico = anno_accademico;
        this.docente = docente;
        this.discenti = discenti;
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
    public DocenteLiteDTO getDocente() {
        return docente;
    }
    public void setDocente(DocenteLiteDTO docente) {
        this.docente = docente;
    }
    public List<DiscenteLiteDTO> getDiscenti() {
        return discenti;
    }
    public void setDiscenti(List<DiscenteLiteDTO> discenti) {
        this.discenti = discenti;
    }
}
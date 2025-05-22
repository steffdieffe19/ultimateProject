package com.example.demo.data.DTO;

import com.example.demo.data.entity.Docente;

import java.util.List;

public class CorsoDTO {

    private String nome;
    private Integer anno_accademico;
    private DocenteLiteDTO docente;
    private List<DiscenteLiteDTO> discenti;

    public CorsoDTO() {

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

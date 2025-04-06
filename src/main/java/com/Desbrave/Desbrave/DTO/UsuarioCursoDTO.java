package com.Desbrave.Desbrave.DTO;

import java.time.LocalDate;

public class UsuarioCursoDTO {
    private String tituloCurso;
    private double progresso;
    private LocalDate dataInicio;

    
    public UsuarioCursoDTO(String tituloCurso, double progresso, LocalDate dataInicio) {
        this.tituloCurso = tituloCurso;
        this.progresso = progresso;
        this.dataInicio = dataInicio;
    }

   
    public String getTituloCurso() {
        return tituloCurso;
    }

    public void setTituloCurso(String tituloCurso) {
        this.tituloCurso = tituloCurso;
    }

    public double getProgresso() {
        return progresso;
    }

    public void setProgresso(double progresso) {
        this.progresso = progresso;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
}

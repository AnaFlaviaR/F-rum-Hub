package com.exemplo.forumhub.dto;

import jakarta.validation.constraints.NotBlank;

public class TopicoRequestDTO {
    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    @NotBlank
    private String autor;

    @NotBlank
    private String curso;

    public String getTitulo() { return titulo; }
    public String getMensagem() { return mensagem; }
    public String getAutor() { return autor; }
    public String getCurso() { return curso; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public void setAutor(String autor) { this.autor = autor; }
    public void setCurso(String curso) { this.curso = curso; }
}

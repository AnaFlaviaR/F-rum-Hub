package com.exemplo.forumhub.dto;

import com.exemplo.forumhub.model.Topico;
import java.time.LocalDateTime;

public class TopicoResponseDTO {
    private Long id;
    private String titulo;
    private String mensagem;
    private String autor;
    private String curso;
    private String status;
    private LocalDateTime dataCriacao;

    public TopicoResponseDTO(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.autor = topico.getAutor();
        this.curso = topico.getCurso();
        this.status = topico.getStatus().name();
        this.dataCriacao = topico.getDataCriacao();
    }
}

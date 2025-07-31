package com.exemplo.forumhub.controller;

import com.exemplo.forumhub.dto.TopicoRequestDTO;
import com.exemplo.forumhub.dto.TopicoResponseDTO;
import com.exemplo.forumhub.model.Topico;
import com.exemplo.forumhub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public ResponseEntity<?> criarTopico(@RequestBody @Valid TopicoRequestDTO dto) {
        if (repository.existsByTituloAndMensagem(dto.getTitulo(), dto.getMensagem())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tópico duplicado.");
        }
        Topico topico = new Topico();
        BeanUtils.copyProperties(dto, topico);
        repository.save(topico);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TopicoResponseDTO(topico));
    }

    @GetMapping
    public List<TopicoResponseDTO> listarTopicos() {
        return repository.findAll().stream().map(TopicoResponseDTO::new).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(topico -> ResponseEntity.ok(new TopicoResponseDTO(topico)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid TopicoRequestDTO dto) {
        return repository.findById(id)
                .map(topico -> {
                    BeanUtils.copyProperties(dto, topico, "id", "dataCriacao", "status");
                    repository.save(topico);
                    return ResponseEntity.ok(new TopicoResponseDTO(topico));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

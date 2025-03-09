package br.com.fiap.shoexpress_api.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import br.com.fiap.shoexpress_api.model.Shoes;

@RestController
public class ShoesControler {

    private List<Shoes> shoes_repository = new ArrayList<>();

    // Index - Listar todos os sapatos
    @GetMapping("/shoes")
    public List<Shoes> index() {
        return shoes_repository;
    }

    // Create - Criar um novo sapato
    @PostMapping("/shoes")
    public ResponseEntity<Shoes> create(@RequestBody Shoes shoes) {
        shoes_repository.add(shoes);
        return ResponseEntity.status(201).body(shoes);
    }

    // Get - Mostrar sapato pelo {ID}
    @GetMapping("/shoes/{id}")
    public ResponseEntity<Shoes> get(@PathVariable int id) {
        var shoes = shoes_repository.stream()
            .filter(s -> s.getId() == id)
            .findFirst();

        if (shoes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(shoes.get());
    }
}
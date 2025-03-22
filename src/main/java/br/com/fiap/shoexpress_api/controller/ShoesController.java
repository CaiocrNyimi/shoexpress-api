package br.com.fiap.shoexpress_api.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.shoexpress_api.model.Shoes;

@RestController
@RequestMapping("/shoes")
public class ShoesController {

    private List<Shoes> shoes_repository = new ArrayList<>();

    // 1.Index - Listar todos os sapatos
    @GetMapping
    public List<Shoes> index() {
        return shoes_repository;
    }

    // 2.Create - Criar um novo sapato
    @PostMapping
    public ResponseEntity<Shoes> create(@RequestBody Shoes shoes) {
        shoes_repository.add(shoes);
        return ResponseEntity.status(201).body(shoes);
    }

    // 3.Get - Mostrar sapato pelo {ID}
    @GetMapping("{id}")
    public Shoes get(@PathVariable int id) {
        return getShoes(id);
    }

    // 4.Update - Atualizar sapato pelo {ID}
    @PutMapping("{id}")
    public Shoes update(@PathVariable int id, @RequestBody Shoes shoes) {
        shoes_repository.remove(getShoes(id));
        shoes.setId(id);
        shoes_repository.add(shoes);

        return shoes;
    }

    // 5.Delete - Deletar sapato pelo {ID}
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        shoes_repository.remove(getShoes(id));
    }

    private Shoes getShoes(int id) {
        return shoes_repository.stream()
            .filter(s -> s.getId() == id)
            .findFirst()
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sapato não encontrado")
            );
    }
}
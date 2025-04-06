package br.com.fiap.shoexpress_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import br.com.fiap.shoexpress_api.repository.ShoesRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/shoes")
public class ShoesController {

    @Autowired
    private ShoesRepository repository;

    // 1.Index - Listar todos os sapatos
    @GetMapping
    public List<Shoes> index() {
        return repository.findAll();
    }

    // 2.Create - Criar um novo sapato
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Shoes create(@RequestBody @Valid Shoes shoes) {
        return repository.save(shoes);
    }

    // 3.Get - Mostrar sapato pelo {ID}
    @GetMapping("{id}")
    public Shoes get(@PathVariable Long id) {
        return getShoes(id);
    }

    // 4.Update - Atualizar sapato pelo {ID}
    @PutMapping("{id}")
    public Shoes update(@PathVariable Long id, @RequestBody Shoes shoes) {
        getShoes(id);
        shoes.setId(id);
        return repository.save(shoes);
    }

    // 5.Delete - Deletar sapato pelo {ID}
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.delete(getShoes(id));
    }

    private Shoes getShoes(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Sapato não encontrado! (ID: " + id + ")"));
    }
}
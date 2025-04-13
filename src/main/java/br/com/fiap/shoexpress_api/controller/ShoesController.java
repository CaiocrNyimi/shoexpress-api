package br.com.fiap.shoexpress_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/shoes")
@Slf4j
@Cacheable(value = "shoes")
public class ShoesController {

    @Autowired
    private ShoesRepository repository;

    // 1.Index - Listar todos os sapatos
    @GetMapping
    @Cacheable(value = "shoes")
    @Operation(summary = "Listar todos sapatos", description = "Lista todos os sapatos do site sem restrição", responses = { @ApiResponse(responseCode = "200", description = "Lista de todos os sapatos") }, tags = "Shoes")
    public List<Shoes> index() {
        return repository.findAll();
    }

    // 2.Create - Criar um novo sapato
    @PostMapping
    @CacheEvict(value = "shoes", allEntries = true)
    @Operation(summary = "Criar novo sapato", description = "Cria um novo sapato no sistema", responses = { @ApiResponse(responseCode = "201", description = "Sapato criado com sucesso") }, tags = "Shoes")
    @ResponseStatus(HttpStatus.CREATED)
    public Shoes create(@RequestBody @Valid Shoes shoes) {
        return repository.save(shoes);
    }

    // 3.Get - Mostrar sapato pelo {ID}
    @GetMapping("{id}")
    @Operation(summary = "Mostrar sapato por ID", description = "Retorna um sapato específico por ID", responses = { @ApiResponse(responseCode = "200", description = "Sapato encontrado") }, tags = "Shoes")
    public Shoes get(@PathVariable Long id) {
        return getShoes(id);
    }

    // 4.Update - Atualizar sapato pelo {ID}
    @PutMapping("{id}")
    @CacheEvict(value = "shoes", allEntries = true)
    @Operation(summary = "Atualizar sapato por ID", description = "Atualiza um sapato específico por ID", responses = { @ApiResponse(responseCode = "200", description = "Sapato atualizado com sucesso") }, tags = "Shoes")
    public Shoes update(@PathVariable Long id, @RequestBody Shoes shoes) {
        getShoes(id);
        shoes.setId(id);
        return repository.save(shoes);
    }

    // 5.Delete - Deletar sapato pelo {ID}
    @DeleteMapping("{id}")
    @CacheEvict(value = "shoes", allEntries = true)
    @Operation(summary = "Deletar sapato por ID", description = "Deleta um sapato específico por ID", responses = { @ApiResponse(responseCode = "204", description = "Sapato deletado com sucesso") }, tags = "Shoes")
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
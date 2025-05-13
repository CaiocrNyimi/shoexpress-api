package br.com.fiap.shoexpress_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import br.com.fiap.shoexpress_api.model.ShoesFilter;
import br.com.fiap.shoexpress_api.model.User;
import br.com.fiap.shoexpress_api.model.UserRole;
import br.com.fiap.shoexpress_api.repository.ShoesRepository;
import br.com.fiap.shoexpress_api.specification.ShoesSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/shoes")
@Slf4j
public class ShoesController {

    @Autowired
    private ShoesRepository repository;

    // 1.Index - Listar todos os sapatos
    @GetMapping
    @Operation(summary = "Listar todos sapatos", description = "Lista todos os sapatos do site sem restrição", 
               responses = { @ApiResponse(responseCode = "200", description = "Lista de todos os sapatos") }, tags = "Shoes")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Page<Shoes> index(@AuthenticationPrincipal User user, ShoesFilter filter,
            @PageableDefault(size = 5, sort = "name") Pageable pageable) {
        Specification<Shoes> specification = ShoesSpecification.withFilters(filter);
        return repository.findAll(specification, pageable);
    }

    // 2.Create - Criar um novo sapato
    @PostMapping
    @Operation(summary = "Criar novo sapato", description = "Cria um novo sapato no sistema", 
               responses = { @ApiResponse(responseCode = "201", description = "Sapato criado com sucesso") }, tags = "Shoes")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Shoes create(@RequestBody @Valid Shoes shoes, @AuthenticationPrincipal User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token não fornecido ou inválido");
        }
        if (shoes.getUser() == null) {
            shoes.setUser(user);
        } else if (!shoes.getUser().equals(user)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "POST não pertence ao usuário autenticado!");
        }
        return repository.save(shoes);
    }

    // 3.Get - Mostrar sapato pelo {ID}
    @GetMapping("{id}")
    @Operation(summary = "Mostrar sapato por ID", description = "Retorna um sapato específico por ID", responses = { @ApiResponse(responseCode = "200", description = "Sapato encontrado") }, tags = "Shoes")
    public Shoes get(@PathVariable Long id, @AuthenticationPrincipal User user) {
        return getShoes(id);
    }

    // 4.Update - Atualizar sapato pelo {ID}
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar sapato por ID", description = "Atualiza um sapato específico por ID",
               responses = { @ApiResponse(responseCode = "200", description = "Sapato atualizado com sucesso") }, tags = "Shoes")
    @PreAuthorize("hasRole('ADMIN')")
    public Shoes update(@PathVariable Long id, @RequestBody @Valid Shoes shoes) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setName(shoes.getName());
                    existing.setBrand(shoes.getBrand());
                    existing.setPrice(shoes.getPrice());
                    existing.setImage(shoes.getImage());
                    existing.setCategory(shoes.getCategory());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sapato não encontrado"));
    }

    // 5.Delete - Deletar sapato pelo {ID}
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar sapato por ID", description = "Deleta um sapato específico por ID",
               responses = { @ApiResponse(responseCode = "204", description = "Sapato deletado com sucesso") }, tags = "Shoes")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    private Shoes getShoes(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Sapato não encontrado! (ID: " + id + ")"));
    }
}
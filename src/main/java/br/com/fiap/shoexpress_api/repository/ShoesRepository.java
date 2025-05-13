package br.com.fiap.shoexpress_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.shoexpress_api.model.Shoes;
import br.com.fiap.shoexpress_api.model.User;

public interface ShoesRepository  extends JpaRepository<Shoes, Long>, JpaSpecificationExecutor<Shoes>{

    Page<Shoes> findByUser(User user, Pageable pageable);
    
}
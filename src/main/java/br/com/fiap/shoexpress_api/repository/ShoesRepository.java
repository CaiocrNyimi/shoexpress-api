package br.com.fiap.shoexpress_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.shoexpress_api.model.Shoes;

public interface ShoesRepository  extends JpaRepository<Shoes, Long>, JpaSpecificationExecutor<Shoes>{
    
}
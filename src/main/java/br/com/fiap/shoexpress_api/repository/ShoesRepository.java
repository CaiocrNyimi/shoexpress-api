package br.com.fiap.shoexpress_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.shoexpress_api.model.Shoes;

public interface ShoesRepository  extends JpaRepository<Shoes, Long>{
    
}
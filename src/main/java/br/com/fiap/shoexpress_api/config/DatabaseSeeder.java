package br.com.fiap.shoexpress_api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.shoexpress_api.model.Shoes;
import br.com.fiap.shoexpress_api.repository.ShoesRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {
    
    @Autowired
    private ShoesRepository shoesRepository;

    @PostConstruct
    public void init() {
        var shoes = List.of(
            Shoes.builder()
                .name("Air Max 90")
                .brand("Nike")
                .price(120.00)
                .image("https://teste.png")
                .category("Basquete")
                .build(),
            Shoes.builder()
                .name("Ultraboost")
                .brand("Adidas")
                .price(150.00)
                .image("https://teste.png")
                .category("Corrida")
                .build(),
            Shoes.builder()
                .name("Chuck Taylor All Star")
                .brand("Converse")
                .price(60.00)
                .image("https://teste.png")
                .category("Casual")
                .build()
        );
        
        shoesRepository.saveAll(shoes);
    }
}

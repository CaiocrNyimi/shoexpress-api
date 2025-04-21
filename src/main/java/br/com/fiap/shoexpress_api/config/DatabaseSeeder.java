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
                .build(),
            Shoes.builder()
                .name("Gel Nimbus 25")
                .brand("Asics")
                .price(180.00)
                .image("https://teste.png")
                .category("Corrida")
                .build(),
            Shoes.builder()
                .name("Superstar")
                .brand("Adidas")
                .price(90.00)
                .image("https://teste.png")
                .category("Casual")
                .build(),
            Shoes.builder()
                .name("Kyrie Infinity")
                .brand("Nike")
                .price(140.00)
                .image("https://teste.png")
                .category("Basquete")
                .build(),
            Shoes.builder()
                .name("Classic Leather")
                .brand("Reebok")
                .price(70.00)
                .image("https://teste.png")
                .category("Casual")
                .build(),
            Shoes.builder()
                .name("Metcon 8")
                .brand("Nike")
                .price(130.00)
                .image("https://teste.png")
                .category("Treino")
                .build(),
            Shoes.builder()
                .name("Nano X3")
                .brand("Reebok")
                .price(110.00)
                .image("https://teste.png")
                .category("Treino")
                .build(),
            Shoes.builder()
                .name("Mercurial Vapor 14")
                .brand("Nike")
                .price(230.00)
                .image("https://teste.png")
                .category("Futebol")
                .build(),
            Shoes.builder()
                .name("Predator Accuracy")
                .brand("Adidas")
                .price(210.00)
                .image("https://teste.png")
                .category("Futebol")
                .build(),
            Shoes.builder()
                .name("SB Dunk Low")
                .brand("Nike")
                .price(130.00)
                .image("https://teste.png")
                .category("Skate")
                .build(),
            Shoes.builder()
                .name("Busenitz Pro")
                .brand("Adidas")
                .price(100.00)
                .image("https://teste.png")
                .category("Skate")
                .build(),
            Shoes.builder()
                .name("Tiempo Legend 9")
                .brand("Nike")
                .price(190.00)
                .image("https://teste.png")
                .category("Futebol")
                .build()
        );    
        
        shoesRepository.saveAll(shoes);
    }
}

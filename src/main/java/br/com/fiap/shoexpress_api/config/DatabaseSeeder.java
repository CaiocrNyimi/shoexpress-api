package br.com.fiap.shoexpress_api.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.shoexpress_api.model.Shoes;
import br.com.fiap.shoexpress_api.model.User;
import br.com.fiap.shoexpress_api.model.UserRole;
import br.com.fiap.shoexpress_api.repository.ShoesRepository;
import br.com.fiap.shoexpress_api.repository.UserRepository;
import jakarta.annotation.PostConstruct;

@Configuration
public class DatabaseSeeder {
    
    @Autowired
    private ShoesRepository shoesRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {

        var admin = User.builder()
                        .email("admin@fiap.com.br")
                        .password(passwordEncoder.encode("admin"))
                        .role(UserRole.ADMIN)
                        .build();

        var person = User.builder()
                        .email("user@fiap.com.br")
                        .password(passwordEncoder.encode("12345"))
                        .role(UserRole.USER)
                        .build();

        userRepository.saveAll(List.of(admin, person));

        var shoes = List.of(
            Shoes.builder()
                .name("Air Max 90")
                .brand("Nike")
                .price(120.00)
                .image("https://teste.png")
                .category("Basquete")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("Ultraboost")
                .brand("Adidas")
                .price(150.00)
                .image("https://teste.png")
                .category("Corrida")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("Chuck Taylor All Star")
                .brand("Converse")
                .price(60.00)
                .image("https://teste.png")
                .category("Casual")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("Gel Nimbus 25")
                .brand("Asics")
                .price(180.00)
                .image("https://teste.png")
                .category("Corrida")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("Superstar")
                .brand("Adidas")
                .price(90.00)
                .image("https://teste.png")
                .category("Casual")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("Kyrie Infinity")
                .brand("Nike")
                .price(140.00)
                .image("https://teste.png")
                .category("Basquete")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("Classic Leather")
                .brand("Reebok")
                .price(70.00)
                .image("https://teste.png")
                .category("Casual")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("Metcon 8")
                .brand("Nike")
                .price(130.00)
                .image("https://teste.png")
                .category("Treino")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("Nano X3")
                .brand("Reebok")
                .price(110.00)
                .image("https://teste.png")
                .category("Treino")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("Mercurial Vapor 14")
                .brand("Nike")
                .price(230.00)
                .image("https://teste.png")
                .category("Futebol")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("Predator Accuracy")
                .brand("Adidas")
                .price(210.00)
                .image("https://teste.png")
                .category("Futebol")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("SB Dunk Low")
                .brand("Nike")
                .price(130.00)
                .image("https://teste.png")
                .category("Skate")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("Busenitz Pro")
                .brand("Adidas")
                .price(100.00)
                .image("https://teste.png")
                .category("Skate")
                .user(admin)
                .build(),
            Shoes.builder()
                .name("Tiempo Legend 9")
                .brand("Nike")
                .price(190.00)
                .image("https://teste.png")
                .category("Futebol")
                .user(admin)
                .build()
        );
        
        shoesRepository.saveAll(shoes);
    }
}

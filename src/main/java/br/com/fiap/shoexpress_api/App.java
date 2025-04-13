package br.com.fiap.shoexpress_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "ShoeXpress API", version = "v1", description = "API da loja de calçados ShoeXpress", contact = @Contact(name = "Caio Cesar", email = "rm556331@fiap.com.br")))
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}

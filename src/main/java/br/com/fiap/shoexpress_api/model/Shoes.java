package br.com.fiap.shoexpress_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório!")
    @Pattern(regexp = "^[a-zA-Z0-9 //-]+$", message = "Caracteres especiais não são permitidos.")
    @Size(min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres!")
    private String name;

    @NotBlank(message = "Marca é obrigatória!")
    @Pattern(regexp = "^[a-zA-Z0-9 //-]+$", message = "Caracteres especiais não são permitidos.")
    @Size(min = 2, max = 25, message = "Marca deve ter entre 2 e 25 caracteres!")
    private String brand;

    @Positive(message = "O preço deve ser um valor positivo.")
    @DecimalMin(value = "50", message = "O preço deve ser pelo menos 50 reais.")
    @DecimalMax(value = "100000.00", message = "O preço não pode passar de 100 mil reais.")
    private double price;

    @NotBlank(message = "URL da imagem é obrigatória!")
    @Pattern(regexp = "^(http|https)://.*$", message = "A URL da imagem deve começar com http:// ou https://")
    private String image;

    @NotBlank(message = "Categoria é obrigatória!")
    @Pattern( regexp = "^(Corrida|Casual|Skate|Basquete|Futebol|Futsal|Treino)$", 
              message = "A categoria deve ser: Corrida, Casual, Treino, Skate, Basquete, Futebol, Futsal.")
    private String category;

    @JsonIgnore
    @ManyToOne
    private User user;
}

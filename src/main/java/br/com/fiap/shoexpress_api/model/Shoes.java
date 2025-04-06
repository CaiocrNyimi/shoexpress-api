package br.com.fiap.shoexpress_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
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
    @Pattern( regexp = "^(Corrida|Casual|Skate|Basquete|Futebol|Futsal)$", 
              message = "A categoria deve ser: Corrida, Casual, Skate, Basquete, Futebol ou Futsal.")
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

package br.com.fiap.shoexpress_api.model;

import java.util.Random;

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
    @DecimalMax(value = "100000.00", message = "O preço não pode exceder 100 mil reais.")
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

}

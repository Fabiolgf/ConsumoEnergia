package br.com.alura.consumoenergia.eletrodomestico.dominio;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Eletrodomestico {

    @NotBlank(message = "O nome do aparelho é obrigatório")
    private String nome;

    @NotBlank(message = "O modelo é obrigatório")
    private String modelo;

    @NotBlank(message = "A potência é obrigatório")
    private String potencia;

    public Eletrodomestico(String nome, String modelo, String potencia) {
        this.nome = nome;
        this.modelo = modelo;
        this.potencia = potencia;
    }
}

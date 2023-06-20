package br.com.alura.consumoenergia.endereco.dominio;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Endereco {

    @NotBlank(message = "O nome da rua é obrigatório")
    private String rua;

    @NotBlank(message = "O numero da rua é obrigatório")
    private String numero;

    @NotBlank(message = "O nome do bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "O nome da cidade é obrigatório")
    private String cidade;

    @NotBlank(message = "O nome do estado é obrigatório")
    private String estado;

    public Endereco(String rua, String numero, String bairro, String cidade, String estado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }
}

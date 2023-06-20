package br.com.alura.consumoenergia.pessoas.dominio;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Pessoa {

    @NotBlank(message = "O nome da pessoa é obrigatório")
    private String nome;

    @NotBlank(message = "A data de nascimento é obrigatório")
    private String dataNascimento;

    @NotBlank(message = "O sexo é obrigatório")
    private String sexo;

    @NotBlank(message = "O grau de parentesco é obrigatório")
    private String parentesco;

    public Pessoa(String nome, String dataNascimento, String sexo, String parentesco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.parentesco = parentesco;
    }
}

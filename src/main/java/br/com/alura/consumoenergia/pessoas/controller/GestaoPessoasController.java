package br.com.alura.consumoenergia.pessoas.controller;

import br.com.alura.consumoenergia.pessoas.dominio.Pessoa;
import br.com.alura.consumoenergia.pessoas.repositorio.RepositorioPessoas;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Path;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
@AllArgsConstructor
public class GestaoPessoasController {

    private Validator validator;
    private RepositorioPessoas repositorioPessoas;

    @PostMapping
    public ResponseEntity<?> cadastrarPessoa(@RequestBody Pessoa pessoa){
        Map<Path, String> violacoeToMap = validar(pessoa);

        if(!violacoeToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoeToMap);
        }
        Pessoa pessoas = new Pessoa(pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getSexo(), pessoa.getParentesco());
        repositorioPessoas.salvar(pessoas);
        return ResponseEntity.ok("Pessoa cadastrada com sucesso ");
    }

    private <T> Map<Path, String> validar(T  form) {
        Set<ConstraintViolation<T>> violacoes =
                validator.validate(form);
        Map<Path, String> violacoeToMap = violacoes.stream()
                .collect(Collectors.toMap(
                        violacao -> violacao.getPropertyPath(), violacao -> violacao.getMessage()));
        return violacoeToMap;
    }
}

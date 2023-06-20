package br.com.alura.consumoenergia.endereco.controller;

import br.com.alura.consumoenergia.endereco.dominio.Endereco;
import br.com.alura.consumoenergia.endereco.repositorio.RepositorioEndereco;
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
@RequestMapping("/endereco")
@AllArgsConstructor
public class CadastroEnderecoController {

    private Validator validator;
    private RepositorioEndereco repositorioEndereco;

    @PostMapping
    public ResponseEntity<?> cadastrarEndereco(@RequestBody Endereco endereco){
        Map<Path, String> violacoeToMap = validar(endereco);

        if(!violacoeToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoeToMap);
        }
        Endereco enderecos = new Endereco(endereco.getRua(), endereco.getNumero(), endereco.getBairro(),
                endereco.getCidade(), endereco.getEstado());
        repositorioEndereco.salvar(enderecos);
        return ResponseEntity.ok("Endereco cadastrado com sucesso");
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

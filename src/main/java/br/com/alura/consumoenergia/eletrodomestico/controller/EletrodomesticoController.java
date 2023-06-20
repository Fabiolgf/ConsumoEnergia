package br.com.alura.consumoenergia.eletrodomestico.controller;

import br.com.alura.consumoenergia.eletrodomestico.dominio.Eletrodomestico;
import br.com.alura.consumoenergia.eletrodomestico.repositorio.RepositorioEletrodomestico;
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
@RequestMapping("/eletrodomestico")
@AllArgsConstructor
public class EletrodomesticoController {

    private Validator validator;

    private RepositorioEletrodomestico repositorioEletrodomestico;

    @PostMapping
    public ResponseEntity<?> cadastrarEletro(@RequestBody Eletrodomestico eletrodomestico){
        Map<Path, String> violacoeToMap = validar(eletrodomestico);

        if(!violacoeToMap.isEmpty()){
            return ResponseEntity.badRequest().body(violacoeToMap);
        }
        Eletrodomestico eletro = new Eletrodomestico(eletrodomestico.getNome(), eletrodomestico.getModelo(), eletrodomestico.getPotencia());
        repositorioEletrodomestico.salvar(eletro);
        return ResponseEntity.ok("Eletrodomestico cadastrada com sucesso");
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

package br.com.alura.consumoenergia.pessoas.repositorio;

import br.com.alura.consumoenergia.pessoas.dominio.Pessoa;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RepositorioPessoas {

    private Set<Pessoa> pessoas;

    public RepositorioPessoas() {
        pessoas =new HashSet<>();
    }

    public void salvar(Pessoa pessoa){
        pessoas.add(pessoa);
    }
}

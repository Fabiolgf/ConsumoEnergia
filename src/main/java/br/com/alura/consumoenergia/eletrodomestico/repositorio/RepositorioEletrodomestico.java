package br.com.alura.consumoenergia.eletrodomestico.repositorio;

import br.com.alura.consumoenergia.eletrodomestico.dominio.Eletrodomestico;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RepositorioEletrodomestico {

    //collectio de eletrodomesticos
    private Set<Eletrodomestico> eletros;

    public RepositorioEletrodomestico(){
        eletros = new HashSet<>();
    }

    public void salvar(Eletrodomestico eletrodomestico) {
        eletros.add(eletrodomestico);
    }

}

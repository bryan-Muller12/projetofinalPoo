package br.unifei.imc;

import br.unifei.imc.exception.SemProcessadoresException;
import br.unifei.imc.observadores.ObservadorChave;
import br.unifei.imc.observavel.Observavel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ProcessadorChave implements Observavel {

    @Getter
    private List<ObservadorChave> observadores;

    public ProcessadorChave(){
        observadores = new ArrayList<>();
    }
    @Override
    public void registrar(ObservadorChave obs) {
        observadores.add(obs);
    }


    public void processar(String frase1) {
        if(observadores.isEmpty())
            throw new SemProcessadoresException();

        String palavras[] = frase1.split(" ");
        for(String palavra : palavras){
            observadores.forEach(obs -> obs.atualiza(palavra));
        }
    }
}

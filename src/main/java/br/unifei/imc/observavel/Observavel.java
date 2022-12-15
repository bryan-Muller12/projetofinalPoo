package br.unifei.imc.observavel;

import br.unifei.imc.observadores.ObservadorChave;

public interface Observavel {

    void registrar(ObservadorChave obs);
}

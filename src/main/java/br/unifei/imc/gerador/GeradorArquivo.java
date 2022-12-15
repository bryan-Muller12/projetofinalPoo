package br.unifei.imc.gerador;

import br.unifei.imc.model.Login;

import java.util.List;

public interface GeradorArquivo {

    void gerarArquivo(List<Login> login, String s);
}

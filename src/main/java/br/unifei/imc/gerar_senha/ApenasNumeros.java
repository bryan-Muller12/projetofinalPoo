package br.unifei.imc.gerar_senha;


import java.util.List;

public class ApenasNumeros implements GeradorSenha {

    private String senha[] = new String[8];;

    @Override
    public String[] gerarSenha() {
        int aux;
        for (int i = 0; i<8; i++) {
            aux = (int) Math.round(Math.random() * 9);
            senha[i] = Integer.toString(aux);
        }
        return senha;
    }
}
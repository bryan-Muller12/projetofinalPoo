package br.unifei.imc.gerar_senha;


import java.util.List;
import java.util.Random;

public class NumerosLetrasSimbolos implements GeradorSenha {

    private String senha[] = new String[8];;

    @Override
    public String[] gerarSenha() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%¨&*()_-?";
        for (int i = 0; i<8; i++) {
            Random r = new Random();
            senha[i] = String.valueOf(alphabet.charAt(r.nextInt(alphabet.length())));
        }
        return senha;
    }
}

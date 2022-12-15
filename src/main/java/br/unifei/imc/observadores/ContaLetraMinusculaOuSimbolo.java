package br.unifei.imc.observadores;

public class ContaLetraMinusculaOuSimbolo implements ObservadorChave {
    private int numCaracter;

    @Override
    public int getNumCaracter() {
        return numCaracter;
    }

    @Override
    public void atualiza(String chave) {
        for(int i = 0; i<8; i++){
            if(Character.isUpperCase(chave.charAt(i)))
                continue;
            else
                if(Character.isDigit(chave.charAt(i)))
                    continue;
                else
                    numCaracter++;

        }
        System.out.printf("\nQtd de Letras Minusculas ou simbolos encontradas na chave: " + numCaracter);
    }
}

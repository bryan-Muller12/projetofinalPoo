package br.unifei.imc.observadores;

public class ContaNumeros implements ObservadorChave {

    private int numCaracter;

    @Override
    public int getNumCaracter() {
        return numCaracter;
    }

    @Override
    public void atualiza(String chave) {
        for(int i = 0; i<8; i++){
            if(Character.isDigit(chave.charAt(i)))
                numCaracter++;
        }
        System.out.printf("\nQtd de numeros encontrados na chave: " + numCaracter);
    }
}

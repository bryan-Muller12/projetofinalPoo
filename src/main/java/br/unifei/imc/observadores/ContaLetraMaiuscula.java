package br.unifei.imc.observadores;

public class ContaLetraMaiuscula implements ObservadorChave {
    private int numCaracter;

    @Override
    public int getNumCaracter() {
        return numCaracter;
    }

    @Override
    public void atualiza(String chave) {
        for (int i = 0; i < 8; i++) {
            System.out.print(chave.charAt(i));
        }
        for(int i = 0; i<8; i++){
            if(Character.isUpperCase(chave.charAt(i))) {
                numCaracter++;
            }
            }
        System.out.printf("\nQtd de Letras Maiusculas encontradas na chave: " + numCaracter);
    }
}

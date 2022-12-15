package br.unifei.imc.senha;

import br.unifei.imc.gerar_senha.NumerosLetrasSimbolos;
import br.unifei.imc.gerar_senha.GeradorSenha;
import lombok.Getter;
import lombok.Setter;


@Getter
public class Senha {
    @Setter
    private GeradorSenha tipoSenha;

    private String senha[] = new String[8];

    public Senha(){
        this.tipoSenha = new NumerosLetrasSimbolos();
    }
    public String[] geraSenha() {
        this.senha = this.tipoSenha.gerarSenha();
        return this.senha;
    }
}

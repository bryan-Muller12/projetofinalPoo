package br.unifei.imc.gerador;

import br.unifei.imc.model.Login;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeradorSenhas implements GeradorArquivo{
    @Override
    public void gerarArquivo(List<Login> login, String s) {
        FileWriter arquivo;
        try {
            arquivo = new FileWriter( s +".csv", true);

            CSVWriter writer = new CSVWriter(arquivo);


            login.forEach(p -> {
                String[] dado = {p.getLogin(), p.getPassword()};
                writer.writeNext(dado);
            });

            arquivo.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}

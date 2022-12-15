package br.unifei.imc.main;

import br.unifei.imc.observadores.ContaLetraMaiuscula;
import br.unifei.imc.observadores.*;
import br.unifei.imc.ProcessadorChave;
import br.unifei.imc.exception.SemProcessadoresException;
import br.unifei.imc.gerador.GeradorArquivo;
import br.unifei.imc.gerador.GeradorLogin;
import br.unifei.imc.gerador.GeradorSenhas;
import br.unifei.imc.model.CsvLogin;
import br.unifei.imc.model.Login;
import br.unifei.imc.senha.Senha;
import br.unifei.imc.gerar_senha.NumerosLetrasSimbolos;
import br.unifei.imc.gerar_senha.NumerosLetras;
import br.unifei.imc.gerar_senha.ApenasNumeros;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    private static GeradorArquivo gerador;
    private static GeradorArquivo gerador_senhas;
    private static List<Login> pessoa;
    private static List<Login> senhas;
    public static void main(String[] args) throws IOException, CsvException {

        String[] test = new String[100];
        int run = 1;
//        /*-------------------------------------------------------------------------------------*/

        /*Tela Usuario*/

        System.out.println("\nBem vindo a tela de login!");
        while (run == 1) {
            Scanner entrada = new Scanner(System.in);
            System.out.println("\nO que deseja fazer?\n\n1-Cadastro\n2-Login\n3-Sair");
            int opc = entrada.nextInt();

            /*Cadastro de Usuario*/
            if (opc == 1) {
                Scanner in = new Scanner(System.in);
                // 1) Informe o seu login
                System.out.println("Login:");
                String login = in.nextLine();
                // 2) Informe a sua senha
                System.out.println("Password: ");
                String password = in.nextLine();

                pessoa = new ArrayList<>();
                pessoa.add(new Login(login,password));

                gerador = new GeradorLogin();
                gerador.gerarArquivo(pessoa, login);

                System.out.printf("Usuário cadastrado com sucesso.");
                System.out.printf("\n");
                System.out.printf("-----------------------------------------------------------");
                /*Login */
            } else if (opc == 2) {

                Reader reader = Files.newBufferedReader(Paths.get("pessoas.csv"));

                CsvToBean<CsvLogin> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(CsvLogin.class)
                        .build();

                List<CsvLogin> pessoas = csvToBean.parse();
                ArrayList<Object> listaPessoa = new ArrayList<>();


                for (CsvLogin pessoa : pessoas)
                    listaPessoa.add(pessoa);


                Scanner in = new Scanner(System.in);
                // 1) Informe o seu login
                System.out.println("Login: ");
                String login = in.nextLine();
                // 2) Informe a sua senha
                System.out.println("Senha:");
                String pass = in.nextLine();

                String juncao = login + ";" + pass;

                boolean verificacao = false;

                int n = listaPessoa.size();

                for(int i =0; i< n; i++ ) {
                 String  auxiliar = listaPessoa.get(i).toString();
                 verificacao = auxiliar.equals(juncao);
                 if (verificacao == true){
                     i = n+1;
                 }
                }
                if (verificacao == true) {
                    System.out.printf("Usuário %s logado com sucesso.", login);
                    /* --------------------------------------------------------------------- */
                    /*Tela Usuario*/
                    while (run == 1) {
                        Scanner entrada2 = new Scanner(System.in);

                        System.out.printf("\n");
                        System.out.printf("-----------------------------------------------------------");

                        System.out.println("\nO que deseja fazer?\n\n1-Criar Senha \n2-Listar Senhas\n3-Sair");
                        int opc_2 = entrada2.nextInt();

                        if (opc_2 == 1) {

                            Senha senha = new Senha();

                            System.out.printf("----------------------------------------------------------- ");
                            System.out.printf("\n");
                            System.out.println("Primeiramente, será gerada uma senha randomica\nVoce deseja qual tipo de senha?");
                            System.out.println("");
                            System.out.println("1-Numérica\n2-Alfanumérico\n3-Caracteres");
                            int tipo = entrada.nextInt();
                            if (tipo == 1) {
                                senha.setTipoSenha(new ApenasNumeros());
                            } else if (tipo == 2) {
                                senha.setTipoSenha(new NumerosLetras());
                            } else
                                senha.setTipoSenha(new NumerosLetrasSimbolos());
                            String s[] = new String[8];
                            String x = "";
                            s = senha.geraSenha();
                            System.out.print("\nSenha gerada para sua conta: ");
                            for (int i = 0; i < 8; i++) {
                                System.out.print(s[i]);
                                x += s[i];
                            }
                            senhas = new ArrayList<>();
                            senhas.add(new Login(login,x));

                            gerador_senhas = new GeradorSenhas();
                            gerador_senhas.gerarArquivo(senhas, login);

                            System.out.printf("\n");
                            System.out.printf("-----------------------------------------------------------");

                            System.out.println("\nO que deseja contar na chave EF78A45b gerada agora?\n\n1-Numeros \n2-Letras Maiúsculas\n3-Letras Minúsculas\n4-Sair");
                            int tip = entrada.nextInt();
                            String chave = "EF78A45b";
                            ProcessadorChave processadorChave;
                            processadorChave = new ProcessadorChave();
                            if (tip == 1){
                                processadorChave.registrar(new ContaNumeros());
                                processadorChave.processar(chave);
                            }
                            else if(tip == 2){
                                processadorChave.registrar(new ContaLetraMaiuscula());
                                processadorChave.processar(chave);
                            }
                            else if(tip == 3){
                                processadorChave.registrar(new ContaLetraMinusculaOuSimbolo());
                                processadorChave.processar(chave);
                            }
                        }
                        else if (opc_2 == 2) {
                            Reader reader2 = Files.newBufferedReader(Paths.get(login+ ".csv"));
                            CSVReader csvReader = new CSVReaderBuilder(reader2).withSkipLines(0).build();

                            List<String[]> senhaz = csvReader.readAll();
                            for (String[] s : senhaz)
                                System.out.println("Senha : " + s[1]);


                        }
                        else  run = 0;}


                }else {
                    System.out.println("Login ou senha inválidos!");}


            } else
                run = 0;
        }

        return;
    }
}
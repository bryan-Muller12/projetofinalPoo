package br.unifei.imc.model;

public class CsvLogin {
        private String login;
        private String password;

        // Gets e Sets omitidos para ficar menor

        public String toString() {
            return  login + ";" + password;
        }
    }

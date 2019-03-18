package br.pucrs.construcao_de_software.pipe_and_filters.app;

import br.pucrs.construcao_de_software.pipe_and_filters.arquivo.Arquivo;

import java.util.ArrayList;
import java.util.HashMap;

public class App {

    private static String nomeArquivo;
    private static HashMap<String, ArrayList<Integer>> mapaPalavras;

    public static void main(String args[]) {

        mapaPalavras = new HashMap<>();
        nomeArquivo = Arquivo.lerNomeArquivo();

        Arquivo.lerArquivo(nomeArquivo, mapaPalavras);
        Arquivo.salvarSaida(nomeArquivo, mapaPalavras);

    }

}
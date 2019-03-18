package br.pucrs.construcao_de_software.pipe_and_filters.app;

import br.pucrs.construcao_de_software.pipe_and_filters.arquivo.Arquivo;

import java.util.ArrayList;
import java.util.HashMap;

public class App {

    private static String fileName;
    private static HashMap<String, ArrayList<Integer>> map;

    public static void main(String args[]) {

        map = new HashMap<>();
        fileName = Arquivo.lerNomeArquivo();

        Arquivo.lerArquivo(fileName, map);
        Arquivo.salvarSaida(fileName, map);

    }

}
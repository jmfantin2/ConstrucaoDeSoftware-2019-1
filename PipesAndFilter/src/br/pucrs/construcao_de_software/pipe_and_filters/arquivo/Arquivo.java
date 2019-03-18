package br.pucrs.construcao_de_software.pipe_and_filters.arquivo;

import br.pucrs.construcao_de_software.pipe_and_filters.singleton.LoggerSingleton;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Arquivo {

    private static final Logger LOGGER = LoggerSingleton.getInstance();
    private static Integer numeroLinha = 0;

    public static String lerNomeArq() {
        Scanner input = new Scanner(System.in);
        System.out.print("Por favor, digite o nome do arquivo-texto de entrada: ");

        String fileName = input.nextLine();
        input.close();

        return fileName;
    }

    public static void lerArquivo(String nomeArquivo, HashMap<String, ArrayList<Integer>> mapaPalavras) {

        LOGGER.log(Level.INFO, String.format("Tentando realizar abertura do arquivo : %s", nomeArquivo));
        try (Stream<String> streamLinhas = Files.lines(Paths.get(nomeArquivo))) {

            streamLinhas.forEach(linha -> {
                numeroLinha++;
                String[] palavrasFiltradas = linha.split("[^\\w']+");
                inserirOcorrenciasPalavra(palavrasFiltradas, mapaPalavras);
            });

        } catch (
                Exception e) {
            LOGGER.log(Level.SEVERE, String.format("Erro ao abrir o arquivo: %s", e.getMessage()));
            System.out.println("ERRO: arquivo inexistente.");
        }
    }

    private static void inserirOcorrenciasPalavra(String[] palavras, HashMap<String, ArrayList<Integer>> mapaPalavras) {

        Arrays.stream(palavras)
                .parallel()
                .forEach(palavra -> {

                    if (palavra.isEmpty())
                        return;
                    if (mapaPalavras.containsKey(palavra)) {
                        mapaPalavras.get(palavra).add(numeroLinha);
                    } else {
                        ArrayList<Integer> linhasOcorrencias = new ArrayList<>();
                        linhasOcorrencias.add(numeroLinha);
                        mapaPalavras.put(palavra, linhasOcorrencias);
                    }
                });
    }

    public static void salvarSaida(String fileName, HashMap<String, ArrayList<Integer>> map) {
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(fileName + "-xref.txt"), "utf-8"))) {

            for (String key : map.keySet()) {
                writer.write(key + ";" + map.get(key) + "\r");
            }

            System.out.println("Análise realizada. Arquivo " + fileName + "-xref.txt gerado");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERRO: não foi possível criar arquivo.");
        }

    }

}
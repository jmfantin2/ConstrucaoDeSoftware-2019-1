package br.pucrs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Arquivo {

	public static String lerNomeArq() {
		Scanner input = new Scanner(System.in);
		System.out.print("Por favor, digite o nome do arquivo-texto de entrada: ");

		String fileName = input.nextLine();
		input.close();

		return fileName;
	}

	public static void lerArquivo(String fileName, HashMap<String, ArrayList<Integer>> map) {

		// Tenta abrir o arquivo
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)))) {
			String line;
			int lineNumber = 0;

			// Le o arquivo, uma linha por vez
			while (reader.ready()) {
				lineNumber++;
				line = reader.readLine();

				// Usa uma REGEX para separar palavras de caracteres especiais
				String[] sArray = line.split("[^\\w']+");

				// Salva num HashMap a palavra e a linha em que ocorreu
				for (String s : sArray) {
					if (s.isEmpty())
						continue;
					if (map.containsKey(s)) {
						map.get(s).add(lineNumber);
					} else {
						ArrayList<Integer> a = new ArrayList<>();
						a.add(lineNumber);
						map.put(s, a);
					}
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO: arquivo inexistente.");
		}
	}

	public static void salvarSaida(String fileName, HashMap<String, ArrayList<Integer>> map) {
		try (Writer writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(fileName + "-xref.txt"), "utf-8"))) {

			// writer.write(map.toString());
			for (String key : map.keySet()) {
				writer.write(key + ";" + map.get(key) + "\r");
			}

			System.out.println("Análise realizada. Arquivo " + fileName + "-xref.txt gerado");
		} catch (Exception e) {
			System.out.println("ERRO: não foi possível criar arquivo.");
		}

	}

}

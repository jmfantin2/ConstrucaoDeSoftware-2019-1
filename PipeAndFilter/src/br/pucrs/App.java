package br.pucrs;

import java.util.ArrayList;
import java.util.HashMap;

public class App {

	private static String fileName;
	private static HashMap<String, ArrayList<Integer>> map;

	public static void main(String args[]) {

		map = new HashMap<>();
		fileName = Arquivo.lerNomeArq();

		Arquivo.lerArquivo(fileName, map);

		Arquivo.salvarSaida(fileName, map);

	}

}

package spellCheck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	private static String filePath = "src/Resources/Oxford_English_Dictionary.txt";
	public static String word;
	private static SpellCheckerTemp sc;

	public static void main(String[] args) {
		loadDictionary();
		runSpellCheck();
	}

	private static void loadDictionary() {
		sc.loadGraph(filePath);
	}

	private static void runSpellCheck() {
		try (Scanner userInput = new Scanner(System.in)) {
			System.out.println("Please enter a word: ");
			word = userInput.next().toLowerCase().replaceAll("[^a-zA-Z]", "");
			sc.checkSpelling(word);
		}
	}

}

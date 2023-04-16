package spellCheck;

import java.util.*;
import java.io.*;

public class SpellChecker {
	public static void main(String[] args) throws FileNotFoundException {
		// Read in the dictionary file and create a hash map
		String fileName = "src/Resources/Oxford_English_Dictionary.txt";
		Scanner dictScanner = new Scanner(new File(fileName));
		try (Scanner userInput = new Scanner(System.in);
				Scanner userInput2 = new Scanner(System.in)) {
			Double startTime = (double) System.nanoTime();
			Double endTime;
			Map<String, Integer> dictionary = new HashMap<>();
			while (dictScanner.hasNextLine()) {
				String line = dictScanner.nextLine().toLowerCase();
				String[] parts = line.split(" ", 2);
				String word = parts[0].replaceAll("[^a-zA-Z]", "");
				dictionary.put(word, word.hashCode());
			}
			dictionary.values().remove(0);
			dictionary.remove("",0);
			dictScanner.close();
			endTime = (Double) (System.nanoTime() - startTime) / Math.pow(10, 9);
			System.out.println(endTime);
			System.out.println(dictionary.size());
			do {
				System.out.println("Would you like to spell check a word or add one to the dictionary?\nEnter one or two: ");
				int userChoice = Integer.parseInt(userInput2.next());
				if (userChoice == 1) {
					System.out.print("What word would you like to spell check? ");
					String word = userInput.nextLine();
					if (dictionary.containsKey(word)) {
						System.out.println("Word spelled correctly");
					} else {
						System.out.println("Word spelled incorrectly.");
						System.out.println(suggestWordReplacements(word, dictionary));
					}
				} else if (userChoice == 2) {
					addToDictionary(dictionary);
				} else {
					System.out.println("Invalid Input please try again");
				}
			} while (true);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, List<String>> suggestWordReplacements(String misspelledWord,
			Map<String, Integer> dictionary) {
		Map<Integer, List<String>> candidates = new HashMap<>();
		for (String word : dictionary.keySet()) {
			int distance = levenshteinDistance(misspelledWord, word);
			if (candidates.containsKey(distance)) {
				candidates.get(distance).add(word);
			} else {
				List<String> list = new ArrayList<>();
				list.add(word);
				candidates.put(distance, list);
			}
		}

		Map<String, List<String>> suggestedReplacements = new HashMap<>();
		int minDistance = Integer.MAX_VALUE;
		for (int distance : candidates.keySet()) {
			if (distance < minDistance) {
				minDistance = distance;
			}
		}

		if (candidates.containsKey(minDistance)) {
			suggestedReplacements.put("Replace with one of the following words:", candidates.get(minDistance));
		}
		return suggestedReplacements;
	}

	public static int levenshteinDistance(String s1, String s2) {
		int[][] distance = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			distance[i][0] = i;
		}
		for (int j = 1; j <= s2.length(); j++) {
			distance[0][j] = j;
		}

		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				int cost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
				distance[i][j] = Math.min(Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1),
						distance[i - 1][j - 1] + cost);
			}
		}

		return distance[s1.length()][s2.length()];
	}

	public static void addToDictionary(Map<String, Integer> dictionary) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("What word would you like to add? ");
		String newWord = userInput.nextLine();
		dictionary.put(newWord, newWord.hashCode());
	}
}

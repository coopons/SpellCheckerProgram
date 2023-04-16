package spellCheck;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import edu.princeton.cs.algs4.DijkstraUndirectedSP;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;

public class Graph {

	private static ArrayList<Vertex> vertices;
	private EdgeWeightedGraph graph;

	public Graph(String filePath) {
		try (Scanner dictScanner = new Scanner(new File(filePath))) {

			Map<String, Integer> dictionary = new HashMap<>();
			while (dictScanner.hasNextLine()) {
				String line = dictScanner.nextLine().toLowerCase();
				String[] parts = line.split(" ", 2);
				String word = parts[0].replaceAll("[^a-zA-Z]", "");
				dictionary.put(word, word.hashCode());
			}
			dictionary.values().remove(0);
			dictionary.remove("", 0);
			dictScanner.close();
			this.graph = new EdgeWeightedGraph(dictionary.size());
			vertices = new ArrayList<>(dictionary.size());
			int index = 0;
			for (String word : dictionary.keySet()) {
				Vertex v = new Vertex(word, index);
				vertices.add(v);
				index++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addVertex(Vertex v) {
		vertices.add(v);
	}

	public void addEdge(Vertex v1, Vertex v2, double weight) {
		Edge e = new Edge(v1.getIndex(), v2.getIndex(), weight);
		graph.addEdge(e);
		v1.addEdge(e);
		v2.addEdge(e);
	}
	
	public void delVertex (Vertex v) {
		//TODO
	}
	
	public void delEdge (Edge e) {
		//TODO
	}

	public static Vertex getVertex(String word) {
		for (Vertex v : vertices) {
			if (v.getWord().equals(word)) {
				return v;
			}
		}
		System.out.println("Vertex not found");
		return null;
	}

	public boolean validateVertex(String word) {
		int v = getVertex(word).getIndex();
		if (v < 0 || v >= vertices.size()) {
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + vertices.size());
		}
		for (Vertex V : vertices) {
			if (V.getWord().equals(word)) {
				return true;
			}
		}
		return false;

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

	public void calculateEdgeWeights() {
		for (int i = 0; i < vertices.size(); i++) {
			for (int j = i + 1; j < vertices.size(); j++) {
				String s1 = vertices.get(i).getWord();
				String s2 = vertices.get(j).getWord();
				int distance = levenshteinDistance(s1, s2);
				if (distance <= 2) { // set maximum edit distance here
					addEdge(vertices.get(i), vertices.get(j), distance);
				}
			}
		}
	}

	public List<Vertex> suggestWordReplacements(String word, int errorMargin) {
		Vertex v = getVertex(word);
		DijkstraUndirectedSP dsp = new DijkstraUndirectedSP(graph, v.getIndex());
		List<Vertex> result = new ArrayList<>();
		for (Vertex u : vertices) {
			if (u != v && dsp.hasPathTo(u.getIndex()) && dsp.distTo(u.getIndex()) <= errorMargin) {
				result.add(u);
			}
		}
		return result;
	}

}
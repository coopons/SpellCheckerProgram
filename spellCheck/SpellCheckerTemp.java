package spellCheck;

import java.util.List;

public class SpellCheckerTemp {

	private Graph graph;
	private int errorMargin;
	
	public SpellCheckerTemp(int errorMargin) {
		this.errorMargin = errorMargin;
	}
	
	public void loadGraph(String graphFile) {
		if (graphFile == null) {
			throw new IllegalStateException("Graph file is not set");
		}
		graph = new Graph(graphFile);
	}

	public boolean isWordSpelledCorrectly(String word) {
		return graph.validateVertex(word);
	}

	public List<Vertex> getSuggestions(String word) {
		return graph.suggestWordReplacements(word, errorMargin);
	}
}

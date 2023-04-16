package spellCheck;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Edge;

public class Vertex {
	
	private String word;
	private int position;
	private ArrayList<Edge> adjacentEdges;
	
	public Vertex(String word, int position) {
		this.word = word;
		this.position = position;
		this.adjacentEdges = new ArrayList<Edge>();
	}
	
	public String getWord() {
		return this.word;
	}
	
	public int getIndex() {
		return this.position;
	}
	
	public ArrayList<Edge> getEdges(){
		if (adjacentEdges.isEmpty()) {
			System.out.println("Vertex has no edges");
		}
		return this.adjacentEdges;
	}

	public void addEdge(Edge e) {
	    if (adjacentEdges == null) {
	        adjacentEdges = new ArrayList<Edge>();
	    }
	    adjacentEdges.add(e);
	}
	
	
	
}

package spellCheck;

public class Edges {

	private Vertex vertex1;
	private Vertex vertex2;
	private double edgeWeight;

	public Edges(Vertex v1, Vertex v2, double weight) {
		if (v1.getIndex() < 0)
			throw new IllegalArgumentException("vertex index must be a non-negative integer");
		if (v2.getIndex() < 0)
			throw new IllegalArgumentException("vertex index must be a non-negative integer");
		this.vertex1 = v1;
		this.vertex2 = v2;
		this.edgeWeight = weight;
	}

	public Vertex getVertex1() {
		return vertex1;
	}

	public Vertex getVertex2() {
		return vertex2;
	}

	public double getWeight() {
		return edgeWeight;
	}

}

package dataScience;
import java.util.ArrayList;
import java.util.Queue;
public class Graph {
	
	/*
	 * A Graph is a structured way to represent relationships between different entities
	 * 	consisting of a set of nodes connected by edges
	 * Graphs are the most powerful, flexible, and expressive abstraction that we can use to 
	 * model relationships between different distributed entities. they are everywhere you look!
	 * 
	 * GRAPH TERMINOLOGY
	 * structure
	 * - two nodes are 'neighbors' if they are directly connected by an edge
	 * - a 'path' between two nodes is defined by a sequence of edges that can be followed to traverse between the two nodes
	 * - the 'length' of a path is the number of edges that make up the path
	 * - a 'cycle' is a path that begins and ends at the same node.
	 * - a 'loop' is an edge directly from a node back to itself. Some graphs allow loops and some do not.
	 * properties
	 * - a node is 'reachable' from another node if a path exists between the two nodes.
	 * - a graph is 'connected' if all nodes are reachable from all other nodes. 
	 * - a graph is 'complete' if every node has an edge connecting it to every other node.
	 * 
	 * types
	 * -'directed' graphs represent situations where relationships are unidirectional(only one direction)
	 * 		(e.g. I follow Dwayne "the Rock" Johnson, but he doesn't follow me back)
	 * -'undirected' graphs represent situations where relationships are bidirectional(applies to both entities)
	 * 		(e.g. I am related to my brother, and he is related to me. Relationship applies to both of us.)
	 * - 'weighted' graphs represent situations where not all relationships between entities are equal.
	 * 		(e.g. Different bonds between atoms in a single molecule all have different bond energies and strengths)
	 * - 'un-weighted' graphs represent situations where all relationships between entities have equal importance.
	 * 		(e.g. all connected words in a word ladder are one letter apart from one another)
	 * 
	 * Exempli Gratia
	 * Social Network ( Nodes:People; Edges:"friendship/following"; Undirected(Facebook)/Directed(Instagram); Unweighted
	 * Chemical Bonds ( Nodes:Atoms; Edges:Bonds(covalent/ionic); Undirected; Weighted)
	 * Interstate Highways (Nodes:Cities; Edges: Highways/Roads; Undirected; Weighted)
	 * Flowcharts (Nodes:Events/Actions; Edges:Transitions; Directed; Unweighted)
	 * The Internet ( Nodes:Devices(phones,computers, etc); Edges:Connection pathways(Bluetooth, WiFi, Ethernet, cables); undirected; weighted/unweighted)
	 * 
	 * --->Linked Data Structures
	 * vs. Linked Lists: linear structure, each node connected to at most one other node.
	 * vs. Trees: Nodes can connect to multiple other nodes, no cycles, parent/child relationship and a single, special root node.
	 * Graphs: No restrictions. its the wild, wild West of the node-based world!
	 * - Graphs have no root node, no parent/child structure
	 * 
	 * 
	 * You may assume vertices given to you are numbered 0 to n-1,
	 * where n-1 is the number of vertices
	 * 
	 */
	/*
	 * Adjacency List
	 * We can represent a graph as a map from nodes to the collection of nodes that each node is adjacent to.
	 * --> Map<Node, Set<Node>>
	 * --> HashMap<Node, HashSet<Node>>
	 * --> Map<Node, Vector<Node>>
	 * --> Vector<Node> //where the Node itself holds a collection of its adjacent neighbors
	 */
	
	/*
	 * Each Node is a vertex in the graph which represents a 
	 */
	private class Node{
		int classNum;
		ArrayList<Integer> edges = new ArrayList<Integer>();
		Node() {
			this.classNum = 0;
		}
		Node(int dataPoint) {
			this.classNum = dataPoint;
		}
		private int getNumClasses() {
			return classNum;
		}
		private int getEdges() {
			return edges.size();
		}
	}
	
	//FIELDS
	ArrayList<Node> courses = new ArrayList<Node>();
	int numElements;
	
	//start, end
	private boolean[][] adjacencyMatrix;
	
	/*
	 * Your constructor must take in a number of vertices 
	 */
	Graph(int n){
		for (int i = 0; i < n; i++) {
			Node course = new Node();
			courses.add(course);
		}
		numElements = n;
		adjacencyMatrix = new boolean[numElements][numElements];
	}
	
	
	/**
	 * Adds an edge from start to end
	 * 
	 * It is reasonable to require your number of vertices to be set before
	 * this function is called (may throw an error if you haven't called setNumberVertices) if you choose.
	 * 
	 * @param start output vertex for edge
	 * @param end input vertex for edge
	 */
	public void addEdge(int start, int end) {
		if (adjacencyMatrix[start][end]) {
			System.out.println("edge already exists at " + start + ", " + end);
		}
		adjacencyMatrix[start][end] = true;
	}
	
	/**
	 * Returns true if there is an edge from start to end
	 * @param start output vertex for edge
	 * @param end input vertex for edge
	 * @return If there is an edge from start to end
	 */
	public boolean hasEdge(int start, int end) {
		return adjacencyMatrix[start][end];
	}
	
	/**
	 * 
	 * @return Returns true if our graph has a cycle anywhere. If our graph is acyclic, return false.
	 */
	public boolean hasCycle() {
		//if has cycle -->return true
		//if acyclic ---->return false
		boolean[] startedAt = new boolean[numElements];
		boolean[] visitedNodes = new boolean[numElements];
		for ( int i = 0; i < numElements; i++ ) {
			return cycleThroughFrom(startedAt, visitedNodes, i);
				//if helper finds cycle...
		}
		return false;
	}
	
	/*
	 * looking for a cycle: take some path, from a node, and end up back at that node
	 * 1. Depth First Search - must check from each vertex
	 * 2. maintain some data structure, signifying where we started from 
	 * 3. For each DFS, maintain a 'visited verticies' for that recursion loop
	 * 
	 * 
	 */
	private boolean cycleThroughFrom(boolean[] startingPoint, boolean[] visited, int startIndex) {
		startingPoint[startIndex] = true;
		visited[startIndex] = true;
		for (int i = 0; i < numElements; i++) {
			if (adjacencyMatrix[startIndex][i]) {
				if(!visited[i]) {
					return cycleThroughFrom(startingPoint, visited, i);
				} else {
					return true;
				}
			}
		}
		visited[startIndex] = false;
		return false;
	}
	
}

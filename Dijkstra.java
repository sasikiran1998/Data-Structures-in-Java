
public class Dijkstra {
public static void main(String[] args) {
		
		Vert vA = new Vert("s");
		Vert vB = new Vert("a");
		Vert vC = new Vert("b");
		Vert vD = new Vert("c");
		Vert vE = new Vert("d");
		
		vA.addNeighbour(new Edge(2,vA,vB));
		vB.addNeighbour(new Edge(3,vB,vC));
		vB.addNeighbour(new Edge(5,vB,vE));
		vC.addNeighbour(new Edge(1,vC,vD));
		
	
		PathFinder shortestPath = new PathFinder();
		shortestPath.ShortestP(vA);
		System.out.println("Minimum distance from s to a: "+vB.getDist());
		System.out.println("Minimum distance from a to b: "+vA.getDist());
		System.out.println("Minimum distance from a to d: "+vC.getDist());

		
		
		System.out.println();
		System.out.println("Shortest Path from s to a: "+shortestPath.getShortestP(vA));
		System.out.println("Shortest Path from a to b: "+shortestPath.getShortestP(vB));
		System.out.println("Shortest Path from b to c: "+shortestPath.getShortestP(vC));
		System.out.println("Shortest Path from a to d: "+shortestPath.getShortestP(vA));

		
	}
}

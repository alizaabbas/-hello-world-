import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * TopologicalSort - this class will take the adjacencyLists of all vertex and print 
 * the topological order of all vertex
 * @author Aliza Abbas
 *
 */
public class TopologicalSort {
	private DoublyLinkedList<DoublyLinkedList<Vertex>> vertexAdList ;
	private DoublyLinkedList<Vertex> vertexList;
	private int[] indegrees;
	/**
	 * parameterized Constructor
	 * @param list - to initialize vertex list
	 */
	public TopologicalSort(DoublyLinkedList<Vertex> list){
		vertexAdList = new DoublyLinkedList<>();
		vertexList = new DoublyLinkedList<>();
		for(int i = 0; i < list.size(); i++){
			vertexList.add(list.get(i));
		}
		indegrees = new int[vertexList.size()];
	}
	/**
	 * findPosIndegree - returns pos of the vertex's indegree
	 * @param v 
	 * @return - index
	 */
	public int findPosIndegree(Vertex v){
		int i = 0;
		while(!(v.getVertex().equals(vertexList.get(i).getVertex()))){
			i++;
		}
		return i;
	}
	/**
	 * addtopo - will add the list to vertexAdList
	 * @param adList
	 */
	public void addtopo(DoublyLinkedList<Vertex> adList){
		vertexAdList.add(adList);
	}
	/**
	 * indegree
	 * @param v
	 * @return - calculate and return the indegree of vertex
	 */
	public int indegree(Vertex v){
		int indegree = 0;
		for(int i = 0; i < vertexAdList.size(); i++){
			int k = 0;
			while(k < vertexAdList.get(i).size()){
				if(vertexAdList.get(i).get(k).getVertex().equals(v.getVertex())) indegree++;
				k++;
			}
		}
		return indegree;
	}
	/**
	 * checkIndegree - will check if the graph is cyclic
	 * @param in
	 */
	public void checkIndegree(int[] in){
		boolean found0 = false;
		for(int i : in ){
			if(i == 0)found0 = true;
		}
		if(found0 == false)throw new IllegalArgumentException("The Graph Is Cyclic, hence topologicl sorting can not be performed.");
	}
	/**
	 * topoSort - does sorting and prints
	 */
	public void topoSort(){
		for(int i = 0; i < vertexList.size(); i++){
			indegrees[i] = indegree(vertexList.get(i));
		}
		checkIndegree(indegrees);
		Queue<Vertex> que = new LinkedList<>();
		Queue<Vertex> sortedQue = new LinkedList<>();
		for(int i = 0; i < vertexList.size(); i++){
			if(indegree(vertexList.get(i)) == 0){
				que.add(vertexList.get(i));
			}
		}
		while(!que.isEmpty()){
			Vertex v = que.poll();
			sortedQue.add(v);
			int count = 0;
			while(!(v.getVertex().equals(vertexList.get(count).getVertex()))){
				count++;
			}
			for(int k = 0 ; k < vertexAdList.get(count).size(); k++){
				int pos = findPosIndegree(vertexAdList.get(count).get(k));
				if(--indegrees[pos] == 0){
					que.add(vertexAdList.get(count).get(k));
				}
			}
		}
		System.out.println();
		System.out.println("Topological Sort: ");
		while(!sortedQue.isEmpty()){
			System.out.print(sortedQue.poll().getVertex() + " ");
		}

	}
}
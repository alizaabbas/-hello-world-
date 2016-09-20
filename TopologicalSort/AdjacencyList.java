import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
/**
 * AdjacencyList Class-
 * description - this class gets the string from the user and through its getList method
 * it returns the adjacency list for a particular vertex by taking in the vertex array
 * and the vertex name in its parameters. It stores the adjacent Vertex in a doublylinkedlist
 * of type Vertex and through its iterator class get access to its data member   
 * @author Aliza Abbas
 *
 */
public class AdjacencyList {
	private String str; 
	/**
	 * AdjacencyList constructor- will take the list of specific vertex adjacency matrix
	 * and set the data member str equal to that parameter
	 * @param list - will store this list in the data member str
	 */
	public AdjacencyList(String list){
		str = list;
	}
	/**
	 * getList method - this method takes in an array of  vertex and the position of 
	 * the vertex the user is working on and returns the vertices adjacent to specific vertex
	 * @param vert  - array of vertices
	 * @param vertPos - position of the vertex in vert array that user is working on
	 * @return - returns the adjacency list for some vertex
	 */
	public String  getList(Vertex[] vert, int vertPos){
		DoublyLinkedList<Vertex> adList = new DoublyLinkedList<>();
		String returnStr = "Vertex " + vert[vertPos]+": ";  
		for(int i = 0; i < vert.length; i++){
			if(str.charAt(i) == '1'){
				adList.add(vert[i]);
			}
		}
		Iterator itr = adList.iterator();

		while(itr.hasNext()){
			returnStr +=  itr.next().toString() + " ";
		}

		return returnStr;
	}

}


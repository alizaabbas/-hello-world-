/**
 * Vertex Class
 * description : this class converts a character into vertex class object
 * through its get, set and toString metod, user can access the data member to modify them
 * @author Aliza Abbas
 *
 */
public class Vertex {
	private char vertex;
	/**
	 * vertex constructor - initializes data member vertex
	 * @param vert - value to be given to vertex
	 */
	public Vertex(char vert){
		vertex = vert;
	}
	/**
	 * getVetex method- returns the vertex character
	 * @return - returns the value of the data member vertex
	 */
	public char getVertex(){
		return vertex;
	}
	/**
	 * setVertex method -- 
	 * @param newVertex - will set this newVertex in the place of old value of the 
	 * data member vertex 
	 * @return - returns the old value of the vertex
	 */
	public char setVertex(char newVertex){
		if(vertex != newVertex){
			char oldVertex = this.vertex;
			this.vertex = newVertex ;
			return oldVertex;
		}
		else
			return vertex;
	}
	/**
	 * toStrig method -
	 * returns value of data member vertex
	 */
	public String toString(){
		String str = Character.toString(vertex);
		return str;

	}
}

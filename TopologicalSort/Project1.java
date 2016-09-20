import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.*;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * Project1 Class - 
 * description - this class will read the input from file and
 * print the adjacency matrix with the help of AdjacencyList class
 * @author Aliza Abbas
 *
 */
public class Project1 {
	public static void main (String[] args) throws FileNotFoundException {
		try{
			int vertexCounter = 0;  // will count the number of vertex
			Scanner scan = new Scanner(new File("src/proj1.txt")); // scan the file
			Vertex[]  vertexList; // store vertex in this array
			String pattern = "[a-zA-Z]"; // to check the pattern of the first line in file
			String c =""; 
			Pattern p = Pattern.compile(pattern);
			while(scan.hasNext(p)){  //while the specified pattern loop would iterate
				c += scan.next(); // c will store vertex in String
				vertexCounter++;
			}
			vertexList = new Vertex[vertexCounter]; // vertexList to store vertex
			for(int i = 0; i < vertexCounter; i++){
				vertexList[i] = new Vertex(c.charAt(i));
			}
			if(c != null){ // if the first line of file is right format ie, all vertex then continue
				String str = scan.nextLine(); 
				int x = 0;
				System.out.println("Adjacency List:");
				while(scan.hasNextLine()){
					str = scan.nextLine(); // str to store the lines after the first without modification with white spaces
					String str1 = "";  // str1 to store  0's and 1's for a vertex adjacency list
					for(int i = 0; i <= str.length()-1; i++){
						if(str.charAt(i) == '0' || str.charAt(i) =='1'){
							str1 += str.charAt(i);
						}
					}
					if(str1.length() == vertexCounter){ // to check if the matrix is valid
						AdjacencyList t = new AdjacencyList(str1); // calling adjacency list to get list
						String concat = t.getList(vertexList, x); 
						System.out.println(concat);
						x++;
					}
					else{
						System.out.println("INVALID ADJACENCY MATRIX!!! " +
								"\nErrors To Check: \n"+
								">> The first Line of file must have vertices with one or more spaces between them." + 	
								"\n>> The Number of 1's and 0's on each line should be exactly the total number of vertices");//error message for invalid matrix
						break; // break loop if invalid matrix
					}
				}
			}
		}
		catch(FileNotFoundException fnf){
			System.out.println(fnf.getMessage());
		}
	}
}

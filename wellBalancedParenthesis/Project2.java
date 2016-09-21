import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.*;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 * project2 - will check and print if the equation is balanced or not 
 * @author Aliza Abbas
 *
 */
public class Project2 { 
	public static void main(String[] args)throws FileNotFoundException{           
		try{
			wellBalanced w = new wellBalanced("src/8queens.txt"); // instantiate and send file name.  
			if(w.isBalanced())System.out.println("Input is balanced.");// if equation balanced
			else
				System.out.println("Error: The user the input does not have well-balanced parentheses, curly brackets, or square brackets");
		}
		catch(FileNotFoundException fnf){
			System.out.println(fnf.getMessage()); //error message if file not found
		}

	}
}

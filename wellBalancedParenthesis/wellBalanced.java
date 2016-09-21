import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
/**
 * wellBalanced class - will see if the file is balanced
 * @author Aliza Abbas
 *
 */
public class wellBalanced {
	private String fileName;
	public wellBalanced(String fn){
		fileName = fn;
	}
	/**
	 * isBalanced - checks if the file is balanced 
	 * @return - boolean value indicating the balance of file
	 * @throws FileNotFoundException
	 */
	public boolean isBalanced() throws FileNotFoundException{

		DynamicArrayStack<Character> stack = new DynamicArrayStack<>();
		Scanner scan = new Scanner(new File(fileName));
		String bracks = "";
		char last ;
		while(scan.hasNextLine()){
			bracks = scan.nextLine();
			System.out.println(bracks);
			for(int i = 0; i < bracks.length(); i++){
				if(bracks.charAt(i) == '{' || bracks.charAt(i) == '(' || bracks.charAt(i)== '[' )
					stack.push(bracks.charAt(i));
				if(bracks.charAt(i) == '}' || bracks.charAt(i) == ')' || bracks.charAt(i) == ']' ){
					if(stack.isEmpty())return false;
					last = stack.top();
					if(last == '{' && bracks.charAt(i)==('}') || 
							last == '(' && bracks.charAt(i)==(')')||
							last == '[' && bracks.charAt(i)==(']'))
						stack.pop();
					else 
						return false;
				}
			}
		}
		return(stack.isEmpty());		
	}
}


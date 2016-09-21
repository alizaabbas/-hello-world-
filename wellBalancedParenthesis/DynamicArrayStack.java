/**
 * DynamicArrayStack Class- 
 * description - creates a stack of anytype
 * @author Aliza Abbas
 *
 */
public class DynamicArrayStack<AnyType> implements Stack<AnyType>
{
	public static final int DEFAULT_CAPACITY = 1024;
	AnyType[] data;
	int topOfStack;
	/**
	 * default constructor --
	 */
	public DynamicArrayStack() { this(DEFAULT_CAPACITY); }
	/**
	 * parameterized constructor -
	 * @param capacity - to initialize the data member with this capacity
	 */
	public DynamicArrayStack(int capacity)
	{
		topOfStack = -1;
		data = (AnyType[]) new Object[capacity];
	}
	/**
	 * returns number of elements
	 */
	public int size()
	{
		return topOfStack+1;
	}
	/**
	 * is Empty - will return boolean value with respect to the size of array
	 */

	public boolean isEmpty()
	{
		return(topOfStack == -1);
	}
	/**
	 * push - inserts elements at the top of stack
	 * @param newValue - value to be inserted
	 */
	public void push(AnyType newValue)
	{
		if(size() != data.length){
			data[++topOfStack] = newValue;
		}
		else{
			AnyType[] temp = (AnyType[]) new Object[2*data.length];
			for(int i = 0; i < size(); i++){
				temp[i] = data[i];
			}
			temp[size()] = newValue;
			data = temp;
			topOfStack++;
		}
	}
	/**
	 * returns the data at the top
	 */
	public AnyType top()
	{
		return data[topOfStack];
	}
	/**
	 * pop - will remove the item at the top from the stack
	 * @return - returns the data removed
	 */
	public AnyType pop()
	{
		if (size() <= (data.length / 4)){
			AnyType[] temp = (AnyType[]) new Object[data.length/2];
			for(int i = 0; i < size(); i++){
				temp[i] = data[i];
			}
			data = temp;
			AnyType oldVal = temp[size()-1];
			temp[size()-1] = null;
			topOfStack--;
			return oldVal;
		}
		else{
			AnyType oldVal = data[size()-1];
			data[size()-1] = null;
			topOfStack--;
			return oldVal;
		}
	}
}
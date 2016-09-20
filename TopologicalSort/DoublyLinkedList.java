import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
/** 
 * DoublyLinkedList Class - 
 * Description - this class will create a doubly linked list
 * of any specified type
 * @author prof. Joseph Svitak && Aliza Abbas
 */
public class DoublyLinkedList<AnyType> implements List<AnyType>
{
	private static class Node<AnyType>
	{
		private AnyType data;
		private Node<AnyType> prev;
		private Node<AnyType> next;

		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n)
		{
			setData(d);
			setPrev(p);
			setNext(n);
		}

		public AnyType getData() { return data; }

		public void setData(AnyType d) { data = d; }

		public Node<AnyType> getPrev() { return prev; }

		public void setPrev(Node<AnyType> p) { prev = p; }

		public Node<AnyType> getNext() { return next; }

		public void setNext(Node<AnyType> n) { next = n; }
	}

	private int theSize;
	private int modCount;
	private Node<AnyType> header;
	private Node<AnyType> trailer;

	public DoublyLinkedList()
	{
		header = new Node<AnyType>(null, null, null);
		trailer = new Node<AnyType>(null, null, null);
		modCount = 0;
		clear();
	}

	public void clear()
	{
		header.setNext(trailer);
		trailer.setPrev(header);
		theSize = 0;
	}

	public int size()
	{
		return theSize;
	}

	public boolean isEmpty()
	{
		return (size() == 0);
	}

	public AnyType get(int index)
	{ 
		return getNode(index).getData();	  

	}
	/**
	 * set method -- set the newValue at the specified index
	 * @param index - will take this index to insert at this position
	 * @param newValue - new value to be inserted at that position
	 * @return - return the value removed at that index
	 */

	public AnyType set(int index, AnyType newValue)
	{
		AnyType oldVal  = getNode(index).getData();	  
		Node<AnyType> pointer  = getNode(index);	  
		pointer.setData(newValue);
		return oldVal;

	}

	public boolean add(AnyType newValue)
	{
		add(size(), newValue);
		return true;
	}
	/** add method - will add new value at given index or the next available index
	 * @param index - add at this index 
	 * @param newValue - value to be inserted
	 */
	public void add(int index, AnyType newValue)
	{
		if(isEmpty()){
			Node<AnyType> newNode = new Node<> (newValue,null,null);
			header.setNext(newNode);
			newNode.setPrev(header);
			newNode.setNext(trailer);
			trailer.setPrev(newNode);
			theSize++;
		}
		else if(index >= 0 || index < size()){
			Node<AnyType> newNode = new Node<> (newValue,null,null);
			Node<AnyType> currNode = getNode(index);
			newNode.setNext(currNode);
			newNode.setPrev(currNode.getPrev());
			currNode.getPrev().setNext(newNode);
			currNode.setPrev(newNode);
			theSize++;
		}
		else{
			if(index < 0)throw new IndexOutOfBoundsException("index out of bounds");
			else{
				Node<AnyType> newNode = new Node<> (newValue,null,null);
				newNode.setPrev(trailer.getPrev());
				newNode.setNext(trailer);
				trailer.getPrev().setNext(newNode);
				theSize++;
			}
		}
	}

	public AnyType remove(int index)
	{
		return remove(getNode(index));
	}

	public Iterator<AnyType> iterator()
	{
		return new LinkedListIterator();    
	}

	private Node<AnyType> getNode(int index)
	{
		return (getNode(index, 0, size()-1));
	}
	/**
	 * getNode method - returns node at specified index
	 * @param index - get node at this index
	 * @param lower - index shouldnt be lesser than lower
	 * @param upper - index should not be greater than upper 
	 * @return - returns node
	 */
	private Node<AnyType> getNode(int index, int lower, int upper)
	{
		Node<AnyType> pointer = header.getNext(); 
		if(index > upper+1 || index < lower)throw new IndexOutOfBoundsException("Index out of bounds!!!");
		else if(size() == 0)throw new IllegalArgumentException("list is empty.");
		else{
			for(int i = lower; i < index; i++){
				pointer = pointer.getNext();
			}
		}
		return pointer;
	}
	/**
	 * remove method -- will remove the given node
	 * @param currNode -- remove this node
	 * @return - return removed node
	 */
	private AnyType remove(Node<AnyType> currNode)
	{
		if(isEmpty())throw new IllegalArgumentException("List is empty");
		else{
			AnyType oldVal = currNode.getData();
			currNode.getPrev().setNext(currNode.getNext());
			currNode.getNext().setPrev(currNode.getPrev());
			theSize--;
			return oldVal;
		}
	}

	private class LinkedListIterator implements Iterator<AnyType>
	{
		private Node<AnyType> current;
		private int expectedModCount;
		private boolean okToRemove;

		LinkedListIterator()
		{
			current = header.getNext();
			expectedModCount = modCount;
			okToRemove = false;
		}

		public boolean hasNext()
		{
			return (current != trailer);
		}

		public AnyType next()
		{
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if (!hasNext())
				throw new NoSuchElementException();

			AnyType nextValue = current.getData();
			current = current.getNext();
			okToRemove = true;
			return nextValue;
		}

		public void remove()
		{
			if (modCount != expectedModCount)
				throw new ConcurrentModificationException();
			if (!okToRemove)
				throw new IllegalStateException();

			DoublyLinkedList.this.remove(current.getPrev());
			expectedModCount++;
			okToRemove = false;
		}
	}
}
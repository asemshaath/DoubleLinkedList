import java.util.Comparator;
import java.util.ListIterator;

/**
 * This class is to make a sorted Linked List (LL) 
 * 
 * @author Asem Shaath 
 *
 * @param <Thing>
 */
public class SortedDoubleLinkedList<Thing> extends BasicDoubleLinkedList<Thing> {

	/**
	 * Comparator object 
	 */
	Comparator<Thing> c; 
	
	/**
	 * 
	 * @param comparator2
	 */
	public SortedDoubleLinkedList(Comparator<Thing> comparator2) 
	{
		
		c =  comparator2; 
		
	} 
	
	/**
	 * This method adds the given element in a sorted way 
	 * 
	 * @param data
	 * @return address of the LL
	 * 
	 */
	public SortedDoubleLinkedList<Thing> add(Thing data)
	{
		
		Node node = new Node(null,data,null); 
		
		if(length == 0) // if there are NO ELEMENTS in the linked list
		{
			
			//node = new Node(null, data, null); 
			//addToEnd(node.getData()); 
			head = node; 
			tail = node; 
			
			length++; 
		}
		else if (c.compare(data, head.getData()) <= 0) // if data is smaller or equal than the head
		{
			
			node = new Node(null, data, head);
			//addToFront(node.getData()); 
			head.setPrev(node);
			head = node; 
			
			length++; 
			
		}
		else if(c.compare(data, tail.getData()) >= 0) // if data is greater or equal than the tail 
		{
			
			//System.out.println("Checking tail :" + tail.getData());
			
			node = new Node(tail, data, null);
			//addToEnd(node.getData()); 
			tail.setNext(node);
			tail = node; 
			
			length++; 
		}
		else 
		{
			
			//System.out.println("else statement "); 
			node = new Node(data);   
			Node pointer = head;
			
			while(pointer.getNext() != null && c.compare(pointer.getData(), data) < 0) 
				pointer = pointer.getNext();	
			
			
			node = new Node(pointer.getPrev(), data, pointer); 
			
			node.getNext().setPrev(node);
			node.getPrev().setNext(node);
			
			length++;
		}
		
		return this; 
		
		
	}
	
	/**
	 * We won't use this method, because it will break our rule which that our LL
	 * must be sorted, so we will throw an error
	 * @throws  UnsupportedOperationException
	 * 
	 */
	public BasicDoubleLinkedList<Thing> addToEnd(Thing data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");  
	}
	
	/**
	 * We won't use this method, because it will break our rule which that our LL
	 * must be sorted, so we will throw an error
	 * @throws  UnsupportedOperationException
	 * 
	 */
	public BasicDoubleLinkedList<Thing> addToFront(Thing data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");  
	}
	
	/**
	 * @return the call of iterator() in the super class 
	 */
	public ListIterator<Thing> iterator() 
	{
		return super.iterator();
	}
	
	/**
	 * /**
	 * This method removes a given data from the LL. 
	 * For instance, if you have this linked list ---> {1, 5, 9, 7, 1, 3} 
	 * and the data that you want to remove is 1, the method will remove 
	 * all the 1s from the LL 
	 * 
	 * @param targetData data we need to remove 
	 * @param comparator
	 * @return a call to the method in the super class
	 *
	 */
	public SortedDoubleLinkedList<Thing> remove(Thing data, Comparator<Thing> comparator) 
	{
		super.remove(data, comparator); 
		return this; 
	}


}

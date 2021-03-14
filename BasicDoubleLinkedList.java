import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 * 
 * @author Asem Shaath
 *
 * @param <Thing>
 * 
 * This class is made for creating a double linked list 
 * 
 */
public class BasicDoubleLinkedList <Thing> implements Iterable <Thing> {

	/*
	 * -----------------------------------------------------------------------------------------------------------
	 * inner classes starts here 
	 * -----------------------------------------------------------------------------------------------------------
	 * 
	 */
	
	/**
	 * 
	 * @author Asem Shaath
	 *
	 * This class has been made to customize the node structure 
	 * 
	 */
	class Node 
	{
		
		/**
		 * The data that the node holds
		 */
		private Thing data;
		
		/**
		 * The address of the previous node 
		 */
		private Node prev; 
		
		/**
		 * The address of the next node 
		 */
		private Node next; 
		
		/**
		 * 
		 * @param prev
		 * @param data
		 * @param next
		 */
		Node(Node prev, Thing data, Node next) 
		{
			
			this.data = data; 
			this.prev = prev; 
			this.next = next; 
			
		}
		
		/**
		 * 
		 * @param data
		 */
		Node(Thing data) 
		{
			
			this.data = data; 
			this.prev = null; 
			this.next = null; 
			
		}

		/**
		 * @return the data
		 */
		public Thing getData() 
		{
			return data;
		}

		/**
		 * @param data the data to set
		 */
		public void setData(Thing data) 
		{
			this.data = data;
		}

		/**
		 * @return the prev
		 */
		public Node getPrev() 
		{
			return prev;
		}

		/**
		 * @param prev the prev to set
		 */
		public void setPrev(Node prev) 
		{
			this.prev = prev;
		}

		/**
		 * @return the next
		 */
		public Node getNext() 
		{
			return next;
		}

		/**
		 * @param next the next to set
		 */
		public void setNext(Node next) 
		{
			this.next = next;
		}
		
		/**
		 *  
		 *  This is a method that returns the node as a string, and it shows where the node ponits
		 *  
		 *  @return node as a string 
		 */
		public String toString()
		{
			
			String str = ""; 
			
			if (prev == null)
				str += "[null <- "; 
			else
				str += "[" + prev.data + " <- ";
			
			str += data + " -> "; 
			
			if (next == null)
				str += "null ]"; 
			else
				str += next.data + " ]";
			
			return str; 
			
		}
		
	}
	
	
	/**
	 * 
	 * @author Asem Shaath
	 * 
	 * This an iterator class. 
	 * We will use the methods of this class to iterate through the linked list 
	 * 
	 *
	 */
	class Iter implements ListIterator<Thing>
	{

		
		/**
		 * The pointer node
		 */
		private Node node;
		 
		
		/**
		 * A constructor that declares the starting position  
		 */
		Iter()
		{
			// starting position 
			node = new Node(null,null,head);
	
		}
				
		
		/**
		 * Checks if a next element exists 
		 * 
		 * @return true if there is an element, otherwise; false 
		 * 
		 */
		@Override
		public boolean hasNext() 
		{

			if(node == null)
			{
				return false; 
			}
			else 
			{
				return node.getNext() != null;
			}
				
		}
		
		/**
		 * Point the pointer after the next node 
		 * 
		 * @return next element 
		 */
		@Override
		public Thing next() 
		{

			Thing next; 
			
			if (hasNext()) 
			{
				
				next = node.getNext().getData();
				
				node.setPrev(node.getNext());
				node.setNext(node.getPrev().getNext());
				
				return next; 
				
			}
			else 
			{
				throw new NoSuchElementException(); 
			}
		}

		
		/**
		 * Checks if a previous element exists 
		 * 
		 * @return true if there is an element, otherwise; false 
		 * 
		 */
		@Override
		public boolean hasPrevious() 
		{
			
			if(node == null)
				return false; 
			else
				return node.getPrev() != null;
		}

		/**
		 * Point the pointer before the previous node 
		 * 
		 * @return previous element 
		 */
		@Override
		public Thing previous() 
		{
			
			Thing prev; 
			
			if (hasPrevious()) 
			{
				
				Node tempPrev = node.getPrev();
				
				prev = tempPrev.getData();
				
				node.setPrev(tempPrev.getPrev());
				node.setNext(tempPrev);
				
				return prev; 
				
			}
			else 
			{
				throw new NoSuchElementException(); 
			}
			
		}

		/**
		 * We don't need this method, so we will throw UnsupportedOperationException
		 */
		@Override
		public int nextIndex() 
		{
			throw new UnsupportedOperationException(); 
		}

		/**
		 * We don't need this method, so we will throw UnsupportedOperationException
		 */
		@Override
		public int previousIndex() 
		{
			throw new UnsupportedOperationException(); 
		}

		/**
		 * We don't need this method, so we will throw UnsupportedOperationException
		 */
		@Override
		public void remove() 
		{
			throw new UnsupportedOperationException(); 
		}

		/**
		 * We don't need this method, so we will throw UnsupportedOperationException
		 */
		@Override
		public void set(Thing e) 
		{
			throw new UnsupportedOperationException(); 
		}

		/**
		 * We don't need this method, so we will throw UnsupportedOperationException
		 */
		@Override
		public void add(Thing e) 
		{
			
			throw new UnsupportedOperationException(); 
			
		}
		
		/**
		 * To print the pointer node as a string
		 */
		public String toString() 
		{
			return node.toString(); 
		}

		
	}
	
	/*
	 * -----------------------------------------------------------------------------------------------------------
	 * methods and fields for the outer class starts here  
	 * -----------------------------------------------------------------------------------------------------------
	 */
	
	/**
	 * The first node 
	 */
	protected Node head;
	
	/**
	 * The last node 
	 */
	protected Node tail;
	
	/**
	 * The size of the linked list 
	 */
	protected int length;  
	
	/**
	 * This constructor is to set the linked list to empty at the beginning 
	 */
	public BasicDoubleLinkedList() 
	{
		head = null;
		tail = null; 
		length = 0;  
		 
		
	}

	/**
	 * @return new Iter 
	 */
	@Override
	public ListIterator<Thing> iterator() throws UnsupportedOperationException, NoSuchElementException
	{
		return new Iter();
	}

	/**
	 * 
	 * @return size of the linked list 
	 */
	public int getSize() 
	{
		return length; 
	}
	
	/**
	 * 
	 * This method adds an element to the end of the linked list 
	 * 
	 * @param data
	 * @return address of the LL 
	 */
	public BasicDoubleLinkedList<Thing> addToEnd(Thing data)
	{
		
		Node lastNode;  
		
		if(head != null && tail != null) // if there are MANY NODES
		{
			
			lastNode = new Node(tail, data, null);
			
			tail.setNext(lastNode);
			tail = lastNode; 
			
			length++;
			
			return this; 
			
		}
		else   // if there are NO NODES 
		{
			
			lastNode = new Node(null, data, null); 
			
			head = lastNode; 
			tail = lastNode;   
			
			length++;
			
			return this; 
			
		}
		
		
	}

	/**
	 * 
	 * This method adds an element to the front of the linked list 
	 * 
	 * @param data
	 * @return address of the LL 
	 */
	public BasicDoubleLinkedList<Thing> addToFront(Thing data)
	{
		
		Node frontNode; 
		
		if(head != null && tail != null) // if there are MANY NODES
		{
			
			frontNode = new Node(null, data, head);
			
			head.setPrev(frontNode);
			head = frontNode; 
			
			length++; 
			
			return this; 
			
		}
		else   // if there are NO NODES 
		{
			
			frontNode = new Node(null, data, null);
			
			head = frontNode; 
			tail = frontNode; 
			
			length++; 
			
			return this; 
			
		}
		
	}
	
	/**
	 * 
	 * @return first element in the LL (Head) 
	 */
	public Thing getFirst() 
	{
		
		if(head == null)
			return null;
		else
			return head.getData(); 
		
	}
	
	/**
	 * 
	 * @return last element in the LL (Tail)
	 */
	public Thing getLast() 
	{
		
		if(tail == null)
			return null;
		else
			return tail.getData(); 
		
	}


	/**
	 * This method removes a given data from the LL. 
	 * For instance, if you have this linked list ---> {1, 5, 9, 7, 1, 3} 
	 * and the data that you want to remove is 1, the method will remove 
	 * all the 1s from the LL 
	 * 
	 * @param targetData data we need to remove 
	 * @param comparator
	 * @return address of the LL
	 * 
	 */
	public BasicDoubleLinkedList<Thing> remove(Thing targetData, Comparator<Thing> comparator)
	{
		
		Node current; 
		current = head; 
		
		while(current != null) 
		{
			
			if(comparator.compare(targetData, current.getData()) == 0) 
			{
				
				// remove the node
				
				if(current == head) 
				{
					
					// remove the head
					
					head = head.getNext();
					head.setPrev(null);
					
					length--;
					
				}
				else if(current == tail)
				{
					
					//remove the tail
					
					tail = tail.getPrev(); 
					tail.setNext(null);
					
					length--; 
					
				}
				else 
				{
					
					// remove the node
					
					current.getNext().setPrev(current.getPrev());
					current.getPrev().setNext(current.getNext());
					
					length--; 
					
				}
				
			}
			
			current = current.getNext(); 
		}
		return this;
	}
	
	/**
	 * Remove the first element 
	 * @return first element
	 */
	public Thing retrieveFirstElement() 
	{
		Thing firstElement; 
		Node newHead; 
		
		if(head == null && tail == null)
		{
			return null;
		}
		else
		{
			
			firstElement = head.getData(); 
			
			// write some code to remove the node
			newHead = new Node(null, head.getNext().getData() ,head.getNext().getNext());
			head = newHead; 
			
			length--; 
			
			return firstElement;
			
		}
		
	}
	
	/**
	 * Remove the last element 
	 * @return last element
	 */
	public Thing retrieveLastElement() 
	{
		
		Thing lastElement; 
		Node newTail; 
		
		if(head == null && tail == null)
		{
			return null;
		}
		else
		{
			
			lastElement = tail.getData(); 
			
			// write some code to remove the node
			newTail = new Node(tail.getPrev().getPrev() ,tail.getPrev().getData() ,null);
			tail = newTail; 
			
			length--; 
			
			return lastElement;
			
		}
		
	}
	
	/**
	 * This method is to convert the LL into an arrayList 
	 * 
	 * @return the arrayList of the nodes without the pointers 
	 * 
	 */
	public ArrayList<Thing> toArrayList()
	{
		
		Node current = head; 
		ArrayList<Thing> returnedArrayList = new ArrayList<Thing>(); 
		
		for (int i = 0; i < length; i++) 
		{
			
			if(current != null) 
			{
				
				returnedArrayList.add(current.getData()); 
				current = current.getNext(); 
				
			}
			
		}
		
		return returnedArrayList; 
	}
	
	/**
	 * @return string of the nodes 
	 */
	public String toString() 
	{
		
		String str = ""; 
		Node current = head; 
		
		while (current != null) 
		{
			str += current + " ";
			current = current.getNext(); 
		}
		return  str; 
		
	}
	
}


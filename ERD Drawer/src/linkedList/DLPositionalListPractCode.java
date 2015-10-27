package linkedList;
import java.util.*;  // for ListIterator
import java.io.*;   //for serialization
import javax.swing.*;  //for JOptionPane

public class DLPositionalListPractCode<T> implements Serializable
{
	private Node head;
	private Node curPos;
	private Node lastItemPos;
	private int listSize;
	private String removeItem;

	public DLPositionalListPractCode()
	{
		//head = new Node(null,head,head);
		//curPos = new Node(null,head,head);
		head = new Node();
		head.next = head;
		head.previous = head;
		curPos = head;
		lastItemPos = null;
		listSize = 0;
	}


	/////////////////////////////////         ListInterator Interface Methods     //////////////////////////////////

		//Inserts the specified element into the list (optional operation).
	public void add(T obj)
	{
		//create new node for T
		Node newNode = new Node(obj, curPos.previous, curPos);
		// sets newNode.previous to last item in list and newNode.next to head that points at first item
		curPos.previous.next = newNode; //sets last item's next to newNode
		curPos.previous = newNode; //sets head's previous to new node
		listSize++;
		lastItemPos = null;
	}

	//curPos points at head each time
	public void addAfter(T obj)
	{
		// adds at end of linked list

		//create new node for T
		Node newNode = new Node(obj, curPos.previous, curPos);
		// sets newNode.previous to last item in list and newNode.next to head that points at first item
		curPos.previous.next = newNode; //sets last item's next to newNode
		curPos.previous = newNode; //sets head's previous to new node
		listSize++;
		lastItemPos = null;
	}


	//moves curPos to newest added node
	public void addAfter2(T obj)
	{
		//adds at end of linked list

		//create new node for T
		Node newNode = new Node(obj, curPos, curPos.next);
		// sets newNode.previous to head and newNode.next to new 2nd item in list
		curPos.next = newNode; //sets next of node curPos is pointing to to newNode
		newNode.next.previous = newNode; //sets head's previous to new node
		curPos = curPos.next;
		listSize++;
		lastItemPos = null;
	}

	//curPos points at head each time
	public void addBefore(T obj)
	{
		//add at beginning of list
		//create new node for T
		Node newNode = new Node(obj, curPos, curPos.next);
		//sets newNode.previous to head and newNode.next to new 2nd item in list
		curPos.next.previous = newNode; //sets 2nd item previous to newNode
		curPos.next = newNode; //points head.hext to newNode
		listSize++;
		lastItemPos = null;
	}

	//curPos points to newest added node
	public void addBefore2(T obj)
	{
		//add at beginning of list
		//create new node for T
		Node newNode = new Node(obj, curPos.previous, curPos);
		//sets newNode.previous to left of curPos and newNode.next to curPos
		curPos.previous = newNode; //sets 2nd item previous to newNode
		newNode.previous.next = newNode; //points head.hext to newNode
		curPos = curPos.previous;
		listSize++;
		lastItemPos = null;
	}

	//Returns true if this list iterator has more elements when traversing the list in the forward direction.
	public boolean hasNext()
	{
		if(curPos.next == head)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	//Returns true if this list iterator has more elements when traversing the list in the reverse direction.
	public boolean hasPrevious()
	{
		if(curPos.previous == head)
		{
		  return false;
		}
		else
		{
			return true;
		}
	}

	//Returns the next element in the list.
	public T next()
	{
		if(!hasNext())
		{
			System.out.println("You are at the end of the list. The first item will be returned:");
		}
		curPos = curPos.next;
		return curPos.previous.value;
	}

	//Returns the index of the element that would be returned by a subsequent call to next.
	public int nextIndex()
	{
		//+1 to account for next
		return currentIndex() + 1;
	}

	//Returns the previous element in the list.
	public T previous()
	{
		if(!hasPrevious())
		{
			System.out.println("You are at the beginning of the list.  The last item will be returned:");
		}
		//moves current position
		curPos = curPos.previous;
		return curPos.value;
	}

	//Returns the index of the element that would be returned by a subsequent call to previous.
	public int previousIndex()
	{
		// -1 to account for previous
		return currentIndex() - 1;
	}

	//Removes what curPos is pointing to and moves curPos to previous
	public void remove()
	{
		lastItemPos = curPos;
		curPos = curPos.previous;
		curPos.next = lastItemPos.next;
		lastItemPos.next.previous = curPos;
		listSize--;
	}

	//prompts user for value to remove
	public void remove2()
	{
		removeItem = JOptionPane.showInputDialog("Input the T you would like to remove.");
		curPos = head.next;
		while(curPos != head && !curPos.value.toString().equals(removeItem))
		{
			if(curPos.next == head)
			{
				System.out.println("The item couldn't be found.\n");
				curPos = head;
			}
			else
			{
				next();
			}
		}
		if(curPos != head)
		{
			remove();
		}
	}

	//Replaces what curPos is pointing to
	public void set(T obj)
	{
	 if(curPos == null)
	 {
		 throw new RuntimeException("There is no established item to set.");
	 }
	 curPos.value = obj;
	}

	/////////////////////////////////          End of ListInterator Methods     //////////////////////////////////


	//Returns the current index of the element

	public int currentIndex()
	{
		int counter = 0;
		Node tempCurPos = new Node();

		if(!hasNext())
		{
			return -1;
		}
		else
		{
			tempCurPos.next = curPos;
			curPos = head.next;

			while(curPos != tempCurPos.next)
			{
				next();
				counter++;
			}
			curPos = tempCurPos.next;
			return counter;
		}
	}

	//returns number of elments in list
	public int size()
	{
		return listSize;
	}
	public String toString(Node printNode)
	{
		return printNode.value.toString();
	}

	//creates a string from the contents of the list
	public String toString()
	{

		String str = "";
		for(Node node = head.next; node != head; node = node.next)
		{
			str += node.value + " ";
		}
		return str;
	}

	public String toBackwardString()
	{
		String str = "";
		for(Node node = head.previous; node != head; node = node.previous)
		{
			str += node.value + " ";
		}
		return str;
	}


	//private inner class for Node
	private class Node implements Serializable
	{
		private T value;
		private Node next;
		private Node previous;

		private Node()
		{
			value = null;
			previous = null;
			next = null;
		}

		private Node(T value)
		{
			this.value = value;
			previous = null;
			next = null;
		}

		private Node(T value, Node previous, Node next)
		{
			this.value = value;
			this.previous = previous;
			this.next = next;
		}
	}
}

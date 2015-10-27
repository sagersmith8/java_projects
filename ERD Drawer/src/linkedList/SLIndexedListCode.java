package linkedList;
import java.io.*;   //for serialization
import javax.swing.*;
import java.util.*;   //for ListIterator

public class SLIndexedListCode<T> implements IndexedList<T>, Serializable
{
	Node head;				// pointer to first node
	private int listSize;			// list size
	private Node nodeAtIndex;	// The node at index position or null if past the end of the list
	private Node nodeBefore;	// The node before index position or null if before the beginning of the list
	private Node pointer;

	public SLIndexedListCode()
	{
		listSize = 4;
	}

	public void add(int index, T obj)
	{
		if(index < 0 || index >= listSize)
		{
			throw new RuntimeException("Index = " + index + " is out of the list bounds");
		}
		if(index == 0)
		{
			head = new Node(obj, head);
		}
		else
		{
			locateNode(index);
			nodeBefore.next = new Node(obj, nodeAtIndex);
		}
		listSize++;
	}

	public boolean isEmpty()
	{
		return listSize == 0;
	}

	public boolean isFull()
	{
		return false;
	}

	public T get(int index)
	{
		if(index < 0 || index >= listSize)
		{
			throw new RuntimeException("Index = " + index + " is out of the list bounds");
		}
		locateNode(index);
		return nodeAtIndex.value;
	}

	public T remove(int index)
	{
		if(index < 0 || index >= listSize)
		{
			throw new RuntimeException("Index = " + index + " is out of the list bounds");
		}

		T removedObj = null;

		if(index == 0)
		{
			removedObj = head.value;
			head = head.next;
		}
		else
		{
			locateNode(index);
			nodeBefore.next = nodeAtIndex.next;
			removedObj = nodeAtIndex.value;
		}
		listSize--;
		return removedObj;
	}

	public T set(int index, T obj)
	{
		T replacedObj = null;

		try
		{
			if(index == 0)
			{
				replacedObj = head.value;
				head.value = obj;
			}
			else
			{
				locateNode(index);
				replacedObj = nodeAtIndex.value;
				nodeAtIndex.value = obj;
			}
		}
		catch(RuntimeException e)
		{
			JOptionPane.showMessageDialog(null, "Index = " + index + " is out of the list bounds", "Out of Bounds Error", JOptionPane.ERROR_MESSAGE);
		}
		return replacedObj;
	}

	public int size()
	{
		return listSize;
	}

	public String toString()
	{
		String message = "";
		for(Node pointer = head; pointer != null; pointer = pointer.next)
		{
			message += pointer.value + " ";
		}
		return message;
	}

	private  void locateNode(int index)
	{
		nodeBefore = null;
		nodeAtIndex = head;

		for(int i = 1; i < listSize && i <= index; i++)
		{
			nodeBefore = nodeAtIndex;
			nodeAtIndex = nodeAtIndex.next;
		}
		if(index == listSize)
		{
			nodeBefore = nodeAtIndex;
			nodeAtIndex = null;
		}
	}

	//private inner class for Node
	private class Node
	{
		private T value;
		private Node next;

		private Node()
		{
			value = null;
			next = null;
		}

		private Node(T value, Node next)
		{
			this.value = value;
			this.next = next;
		}
	}
}

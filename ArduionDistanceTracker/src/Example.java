import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


public class Example
{
	static Scanner scanner = new Scanner(System.in);
	static Stack<String> stack = new Stack<String>();
	static Queue<String> queue = new ArrayDeque<String>();
	public static void main(String[] args) 
	{
		while(scanner.hasNext())
		{
			String str = scanner.nextLine();
			String [] commands = str.split(" ");
			
			if(commands.length == 2)
			{	
				if(commands[0].equals("push"))
				{
					stack.push(commands[1]);
				}
				else if(commands[0].equals("add"))
				{
					queue.add(commands[1]);
				}
			}
			else if(commands.length ==1)
			{
				if(commands[0].equals("remove"))
				{
					try{
						System.out.println(queue.remove());
					}
					catch(NoSuchElementException ex){
						System.out.println("No more elements");
					}
				}
				else if(commands[0].equals("pop"))
				{
					try{
						System.out.println(stack.pop());
					}
					catch(Exception ex){
						System.out.println("No more elements");
					}
				}
			}
		}
	}
}

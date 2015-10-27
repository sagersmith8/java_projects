
package tictactoe;

import java.util.Scanner;

/**
 * @author Sage
 *Dynamic TicTackToe
 */
public class TicTackToe{
	private String[][]status;
	private Scanner scan = new Scanner(System.in);
	private final String PLAYER = "X", CPU = "O";
	private int turn = 1;

	public TicTackToe(){
		System.out.println("Enter the size game you would like to play on");
		int size = Integer.parseInt(scan.nextLine());
		status = new String[size][size];
		
		//Initializes array
		for(int i = 0; i < status.length; i++){
			for(int j = 0; j < status[i].length; j++){
				status[i][j] = " ";
			}
		}

		//prints board initially
		printBoard();

		while(!gameover()){	
			
			//if it is users turn it waits for the user to say where they want to go
			if(turn %2!=0){
				System.out.println("Enter the row and column where you would like to move");
				String [] move = scan.nextLine().split(" ");
				int row, col;
				
				//checks to make sure user has made a valid move
				while(!status[row = Integer.parseInt(move[0])-1][col = Integer.parseInt(move[1])-1].equalsIgnoreCase(" ")){
					System.out.println("Move is occupied try again");
					move = scan.nextLine().split(" ");
				}
				
				status[row][col] = PLAYER;
			}

			//it is computer's turn the computer randomly selects an open place to go
			else{
				int row = (int) (Math.random()*status.length), col = (int) (Math.random()*status[0].length);
				
				//makes sure computer has made a valid move
				while(!status[row][col].equalsIgnoreCase(" ")){
					row = (int) (Math.random()*3);
					col = (int) (Math.random()*3);
				}
				
				status[row][col] = CPU;
			}

			turn++;

			printBoard();
		}
	}
	
	/**
	 * Prints the gameboard
	 */
	private void printBoard(){
		for(int i = 0; i < status.length; i++){
			for(int j = 0; j < status[0].length; j++){
				System.out.print(status[i][j]);

				if(j < status[0].length-1)
					System.out.print("|");

			}
			
			System.out.println();

			if(i < status.length-1){
				for(int j = 0; j < status[0].length; j++){
					System.out.print("--");
				}
			}
			
			System.out.println();
		}
	}

	private boolean gameover(){
		String last = new String();
		int num = 0;
		
		//checks rows
		for(int i = 0; i < status.length; i++){
			for(int j =0; j < status[0].length; j++){				
				if(j>0){
					if(last.equalsIgnoreCase(status[i][j]))
						num++;
					else{
						num = 0;
						j = status[0].length;
					}
				}
				
				if(j!=status[0].length){
					last = status[i][j];
					if(status[i][j].equalsIgnoreCase(" ")){
						num = 0;
						j = status[0].length;
					}
				}
			}

			if(num==status.length-1){
				System.out.println(last + " has won");
				return true;
			}

		}


		//checks collums
		for(int i = 0; i < status[0].length; i++){
			for(int j =0; j < status.length; j++){
				if(j>0){
					if(last.equalsIgnoreCase(status[j][i]))
						num++;
					else{
						num = 0;
						j = status.length;
					}
				}
				
				if(j!=status.length){
					last = status[j][i];
					if(status[j][i].equalsIgnoreCase(" ")){
						num = 0;
						j = status.length;
					}
				}
			}

			if(num==status.length-1){
				System.out.println(last + " has won");
				return true;
			}

		}

		//checks diagonal upper left to bottom right
		for(int i = 0; i < status.length; i++){
			if(i>0){
				if(last.equalsIgnoreCase(status[i][i])){
					num++;
				}
				else{
					num = 0;
					i =status.length;
				}
			}

			if(i!=status.length){
				last = status[i][i];
				if(status[i][i].equalsIgnoreCase(" ")){
					num = 0;
					i = status.length;
				}
			}
		}

		if(num ==status.length-1){
			System.out.println(last + " has won");
			return true;
		}
		
		
		//checks diagonal upper right to lower left
		
		for(int i = 0; i < status.length; i++){	
			
			if(i > 0){
				if(status[i][status.length-1-i].equalsIgnoreCase(last)){
					num++;
				}
				
				else{
					num = 0;
					i = status.length;
				}
			}

			if(i!=-status.length){
				last = status[i][status.length-1-i];
				if(status[i][status.length-1-i].equalsIgnoreCase(" ")){
					num = 0;
					i = status.length;
				}
				;
			}
		}

		if(num ==status.length-1){
			System.out.println(last + " has won");
			return true;
		}
		
		
		//checks cats game
		for(int i = 0; i < status.length; i++){
			for(int j =0; j < status[0].length; j++){
				if(!status[i][j].equals(" ")){
					num++;
				}
			}
		}

		if(num==9){
			System.out.println("Cats Game");
			return true;

		}

		return false;
	}

	public static void main(String[] args) {
		new TicTackToe();
	}

}

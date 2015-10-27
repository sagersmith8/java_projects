package com.company;
/*
 * author@ Audra Landis modified by Bryon Steinwand
 */
import java.io.*;
import java.util.*;
public class BlackjackGame 
{
	private List<Card> myDeck = new ArrayList<Card>();

	public BlackjackGame ()
	{
		myDeck.add(new Card("Two " + "Heart", 2));
		myDeck.add(new Card("Three " + "Heart", 3));
		myDeck.add(new Card("Four " + "Heart", 4));
		myDeck.add(new Card("Five " + "Heart", 5));
		myDeck.add(new Card("Six " + "Heart", 6));
		myDeck.add(new Card("Seven " + "Heart", 7));
		myDeck.add(new Card("Eight " + "Heart", 8));
		myDeck.add(new Card("Nine " + "Heart", 9));
		myDeck.add(new Card("Ten " + "Heart", 10));
		myDeck.add(new Card("Jack " + "Heart", 10));
		myDeck.add(new Card("Queen " + "Heart", 10));
		myDeck.add(new Card("King " + "Heart", 10));

		myDeck.add(new Card("Two "+ "Spade", 2));
		myDeck.add(new Card("Three "+ "Spade", 3));
		myDeck.add(new Card("Four "+ "Spade", 4));
		myDeck.add(new Card("Five "+ "Spade", 5));
		myDeck.add(new Card("Six "+ "Spade", 6));
		myDeck.add(new Card("Seven "+ "Spade", 7));
		myDeck.add(new Card("Eight "+ "Spade", 8));
		myDeck.add(new Card("Nine "+ "Spade", 9));
		myDeck.add(new Card("Ten "+ "Spade", 10));
		myDeck.add(new Card("Jack "+ "Spade", 10));
		myDeck.add(new Card("Queen "+ "Spade", 10));
		myDeck.add(new Card("King "+ "Spade", 10));

		myDeck.add(new Card("Two "+ "Diamond", 2));
		myDeck.add(new Card("Three "+ "Diamond", 3));
		myDeck.add(new Card("Four "+ "Diamond", 4));
		myDeck.add(new Card("Five "+ "Diamond", 5));
		myDeck.add(new Card("Six "+ "Diamond", 6));
		myDeck.add(new Card("Seven "+ "Diamond", 7));
		myDeck.add(new Card("Eight "+ "Diamond", 8));
		myDeck.add(new Card("Nine "+ "Diamond", 9));
		myDeck.add(new Card("Ten "+ "Diamond", 10));
		myDeck.add(new Card("Jack "+ "Diamond", 10));
		myDeck.add(new Card("Queen "+ "Diamond", 10));
		myDeck.add(new Card("King "+ "Diamond", 10));

		myDeck.add(new Card("Two "+ "Club", 2));
		myDeck.add(new Card("Three "+ "Club", 3));
		myDeck.add(new Card("Four "+ "Club", 4));
		myDeck.add(new Card("Five "+ "Club", 5));
		myDeck.add(new Card("Six "+ "Club", 6));
		myDeck.add(new Card("Seven "+ "Club", 7));
		myDeck.add(new Card("Eight "+ "Club", 8));
		myDeck.add(new Card("Nine "+ "Club", 9));
		myDeck.add(new Card("Ten "+ "Club", 10));
		myDeck.add(new Card("Jack "+ "Club", 10));
		myDeck.add(new Card("Queen "+ "Club", 10));
		myDeck.add(new Card("King "+ "Club", 10));

		myDeck.add(new Card("Ace " + "Heart", 1));
		myDeck.add(new Card("Ace "+ "Spade", 1));
		myDeck.add(new Card("Ace "+ "Diamond", 1));
		myDeck.add(new Card("Ace "+ "Club", 1));
	}
	
	public int calculatePoints (List<Card> cardsToCheck)
	{
		int points=0;
		int aceCount = 0;
		
		for(int x = 0; x < cardsToCheck.size(); x++)
		{
			if (cardsToCheck.get(x).getWorth() == 1)
			{
			  points += 11;
			  aceCount++;
			}
			else
			{
				points += cardsToCheck.get(x).getWorth();	
			}
		}
		if (aceCount > 0 && points > 21)  // if we have 1 ace or more and we bust
			points -= 10;
		if (aceCount > 1 && points > 21)  // if we have 2 aces or more and we bust
			points -= 10;
		if (aceCount > 2 && points > 21) // if we have 3 aces or more and we bust
			points -= 10;
		if (aceCount == 4 && points > 21) // if we have 4 aces and we bust
			points -= 10;
		
		return points;
	}
	
	public boolean playGame (BufferedReader in, PrintWriter out)
	{
		boolean gameOver = false;
		int randomCard;
		boolean playerStands = false;
		boolean dealerStands = false;
		int numPlayerCards = 2;
		int numDealerCards = 2;
		
		out.println("Players hand has: ");
		
		List<Card> dealerCards = new ArrayList<Card>();
		List<Card> playerCards = new ArrayList<Card>();
		
		randomCard = (int)(Math.random() * myDeck.size()-1);
		playerCards.add(myDeck.get(randomCard));
		myDeck.remove(randomCard);
		
		randomCard = (int)(Math.random() * myDeck.size()-1);
		dealerCards.add(myDeck.get(randomCard));
		myDeck.remove(randomCard);
		
		randomCard = (int)(Math.random() * myDeck.size()-1);
		playerCards.add(myDeck.get(randomCard));
		myDeck.remove(randomCard);
		
		randomCard = (int)(Math.random() * myDeck.size()-1);
		dealerCards.add(myDeck.get(randomCard));
		myDeck.remove(randomCard);
		
		out.println("cp1" + playerCards.get(0).getName());
		out.println("cp2" + playerCards.get(1).getName());
		out.println("cd2" + dealerCards.get(1).getName());
		out.flush();
		
		try
		{
			while(!gameOver)
			{
				if (calculatePoints(playerCards) >= 21 || calculatePoints(dealerCards) >= 21 || dealerStands)
				{
					//Someone won or both lost 
					//Game Over
					
					//out.println("Dealer shows " + dealerCards.get(0).getName());
					
					if(dealerStands)
					{
						//Dealer wins
						return false;
						
					}
					else
					{
						if(calculatePoints(playerCards) > 21)
						{
							//Player Busted Game over Dealer Wins
							out.println("Player Busts");
							out.flush();
							return false;
						}
						else
						{
							if (calculatePoints(dealerCards) > 21)
							{
								//Dealer Busted Game over Player wins
								out.println("Dealer Busts");
								out.flush();
								return true;
							}
							else
							{
								if(calculatePoints(playerCards) == 21)
								{
									//Player Wins 
									out.println("** CONGRATS 21! **");
									out.flush();
									return true;
								}
								else
								{
									if(calculatePoints(dealerCards) == 21)
									{
										//Dealer Wins 
										out.println("** Dealer got 21! **");
										out.flush();
										return false;
									}
								}
							}
						}
					}
				}
				
				else 
				{
					if(playerStands)
					{
						if(calculatePoints(playerCards) >= calculatePoints(dealerCards) )
						{
							//Dealer gets new cards until they have higher points
							out.println("Dealer draws:");
							randomCard = (int)(Math.random() * myDeck.size()-1);
							Card nextcard = myDeck.get(randomCard);
							dealerCards.add(nextcard);
							numDealerCards++;
							out.println("cd"+ numDealerCards + nextcard.getName());
							out.flush();
							myDeck.remove(randomCard);
						}
						else
						{
							//Dealer has more points than player stop drawing cards
							dealerStands = true;
							out.println("Dealer Stands");
							out.flush();
						}
					}
					
					else
					{
						out.println("Hit or Stand ?");
						out.flush();
						String playerAnswer = in.readLine();
						//String playerAnswer = in.next();

						if(playerAnswer.equalsIgnoreCase("Hit"))
						{
							randomCard = (int)(Math.random() * myDeck.size());
							Card nextcard = myDeck.get(randomCard);
							playerCards.add(nextcard);
							numPlayerCards++;
							out.println("cp" + numPlayerCards +nextcard.getName());
							out.flush();
							myDeck.remove(randomCard);
						}
						
						else if(playerAnswer.equalsIgnoreCase("Stand"))
						{
							out.println("cd1" + dealerCards.get(0).getName());
							out.flush();
							playerStands = true;
						}
					}
				}
				
			}
		}
		
		//catch(IOException e)
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return true;
	}
}


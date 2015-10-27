package com.company;
/*
 * author@ Audra Landis
 */
public class Card 
{
	public String name;
	public int worth;
	
	public Card (String name, int worth)
	{
		this.name = name;
		this.worth = worth;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getWorth() 
	{
		return worth;
	}

	public void setWorth(int worth) 
	{
		this.worth = worth;
	}
	
	
}


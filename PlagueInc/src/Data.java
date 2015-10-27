/**
 * 
 * @author Sage
 *	This class contains all variables that need to be used from class to class
 *	it contains gets and sets for everyone of these variables
 */
import java.math.BigInteger;
import java.util.ArrayList;


public class Data
{
	long cure = 0,funds = 0,transmissionRate = 100, deathRate = 0, remainingConputers = 1000000000, infectedDevices = 0;
	double transmitablitly = 100-transmissionRate;
	double deadlyness = 0;
	boolean cureBegin = false;
	ArrayList<Integer> transmission = new ArrayList<Integer>();
	ArrayList<Integer> symptom = new ArrayList<Integer>();
	
	public Data()
	{
		for (int i = 0; i < 9; i++) 
		{
			transmission.add(1);
			symptom.add(1);
		}
	}
	
	public void setBeginCure(boolean b)
	{
		cureBegin = b;
	}
	public boolean getBeginCure()
	{
		return cureBegin;
	}
	public void setCure(long l)
	{
		cure = l;
	}
	public void setInfectedDevices(long l)
	{
		infectedDevices = l;
	}
	
	public long getInfectedDevices()
	{
		return infectedDevices;
	}
	
	public void setTransmissionRate(long l)
	{
		transmissionRate = l;
	}
	
	public long getTransmissionRate()
	{
		return transmissionRate;
	}
	
	public void setDeathRate(long l)
	{
		deathRate = l;
	}
	
	public long getDeathRate()
	{
		return deathRate;
	}
	
	public void setRemainingComputers(long l)
	{
		remainingConputers = l;
	}
	
	public long getRemainingComputers()
	{
		return remainingConputers;
	}
	
	public void setTransmitability(double d)
	{
		transmitablitly = d;
	}
	
	public double getTransmitability()
	{
		return transmitablitly;
	}
	
	public void setDeadlyness(double d)
	{
		deadlyness = d;
	}
	
	public double getDeadlyness()
	{
		return deadlyness;
	}
		
	public ArrayList<Integer> getInactiveTransmission()
	{
		return transmission;
	}
		
	public ArrayList<Integer> getInactiveSymptom()
	{
		return symptom;
	}

	public void setFunds(long l)
	{
		funds = l;
	}
	public long getFunds() 
	{
		return funds;
	}

	public long getCure() 
	{
		return cure;
	}
	
	
}